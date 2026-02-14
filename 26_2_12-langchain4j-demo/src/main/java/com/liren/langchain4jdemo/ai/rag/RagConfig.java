package com.liren.langchain4jdemo.ai.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RagConfig {
    @Resource
    private EmbeddingModel qwenEmbeddingModel;

    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;

    @Bean
    public ContentRetriever contentRetriever() {
        // 1. 加载整个文档（可以加载多个文档）
        DocumentParser documentParser = new TextDocumentParser();
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs/", documentParser);

        // 2. 创建文档切割器：将每个文档按照段落切割，每部分最多1000字符，每次重叠最多200字符
        DocumentByParagraphSplitter splitter = new DocumentByParagraphSplitter(1000, 200);

        // 3. 使用 EmbeddingStoreIngestor 完成上述操作并且进行入库（目前放在内存中）
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(embeddingStore)
                // 为每个切分文档也就是 TextSegment 的开头添加所属文件名
                .textSegmentTransformer(segment -> TextSegment.from(
                            segment.metadata().getString("file_name") + "\n" + segment.text(),
                            segment.metadata())
                )
                .documentSplitter(splitter)
                .build();
        ingestor.ingest(documents); // 调用ingest才是真的执行上述操作

        // 4. 自定义文档检索器 ContentRetriever（用于AiServices创建时候增加 RAG 功能）
        EmbeddingStoreContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(5)   // 最多 5 个过滤结果
                .minScore(0.75)  // 过滤掉相似度小于 0.75 的结果
                .build();
        return retriever;
    }
}
