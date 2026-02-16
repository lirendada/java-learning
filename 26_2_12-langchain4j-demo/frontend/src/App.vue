<template>
  <div class="chat-container">
    <header class="chat-header">
      <h1>AI 编程小助手</h1>
      <span class="chat-id">会话 ID: {{ memoryId }}</span>
    </header>

    <div class="chat-messages" ref="messagesContainer">
      <div
        v-for="(msg, index) in messages"
        :key="index"
        :class="['message', msg.role === 'user' ? 'message-user' : 'message-ai']"
      >
        <div class="message-avatar">
          {{ msg.role === 'user' ? '我' : 'AI' }}
        </div>
        <div class="message-content">
          <div class="message-text" v-html="formatMessage(msg.content)"></div>
          <div class="message-time">{{ msg.time }}</div>
        </div>
      </div>
      <div v-if="isStreaming" class="message message-ai">
        <div class="message-avatar">AI</div>
        <div class="message-content">
          <div class="message-text streaming">{{ streamingContent }}<span class="cursor">|</span></div>
        </div>
      </div>
    </div>

    <div class="chat-input">
      <textarea
        v-model="inputMessage"
        @keydown.enter.prevent="sendMessage"
        placeholder="请输入你的编程问题..."
        rows="1"
        ref="inputRef"
        :disabled="isStreaming"
      ></textarea>
      <button @click="sendMessage" :disabled="!inputMessage.trim() || isStreaming">
        {{ isStreaming ? '生成中...' : '发送' }}
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick, onUnmounted } from 'vue'
import axios from 'axios'

const API_BASE = 'http://localhost:8080/api'

// 生成随机会话ID
const memoryId = ref(Math.floor(Math.random() * 1000000))
const messages = ref([])
const inputMessage = ref('')
const messagesContainer = ref(null)
const inputRef = ref(null)
const isStreaming = ref(false)
const streamingContent = ref('')

let abortController = null

// 格式化消息内容（处理换行）
const formatMessage = (content) => {
  if (!content) return ''
  return content
    .replace(/\n/g, '<br>')
    .replace(/```(\w+)?\n([\s\S]*?)```/g, '<pre><code>$2</code></pre>')
    .replace(/`([^`]+)`/g, '<code>$1</code>')
}

// 获取当前时间
const getCurrentTime = () => {
  const now = new Date()
  return `${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}`
}

// 滚动到底部
const scrollToBottom = () => {
  nextTick(() => {
    if (messagesContainer.value) {
      messagesContainer.value.scrollTop = messagesContainer.value.scrollHeight
    }
  })
}

// 发送消息
const sendMessage = async () => {
  const content = inputMessage.value.trim()
  if (!content || isStreaming.value) return

  // 添加用户消息
  messages.value.push({
    role: 'user',
    content: content,
    time: getCurrentTime()
  })

  inputMessage.value = ''
  scrollToBottom()

  // 开始流式响应
  isStreaming.value = true
  streamingContent.value = ''

  try {
    const response = await fetch(
      `${API_BASE}/ai/chat?memoryId=${memoryId.value}&message=${encodeURIComponent(content)}`,
      {
        method: 'GET',
        headers: {
          'Accept': 'text/event-stream'
        }
      }
    )

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }

    const reader = response.body.getReader()
    const decoder = new TextDecoder()
    let buffer = ''

    while (true) {
      const { done, value } = await reader.read()
      if (done) break

      buffer += decoder.decode(value, { stream: true })

      // 处理 SSE 数据
      const lines = buffer.split('\n')
      buffer = lines.pop() // 保留最后一个可能不完整的行

      for (const line of lines) {
        if (line.startsWith('data:')) {
          const data = line.slice(5).trim()
          if (data) {
            streamingContent.value += data
            scrollToBottom()
          }
        }
      }
    }

    // 处理剩余数据
    if (buffer.startsWith('data:')) {
      const data = buffer.slice(5).trim()
      if (data) {
        streamingContent.value += data
      }
    }

    // 添加AI回复到消息列表
    messages.value.push({
      role: 'assistant',
      content: streamingContent.value,
      time: getCurrentTime()
    })

  } catch (error) {
    console.error('请求失败:', error)
    messages.value.push({
      role: 'assistant',
      content: '抱歉，请求出现错误，请稍后重试。',
      time: getCurrentTime()
    })
  } finally {
    isStreaming.value = false
    streamingContent.value = ''
    scrollToBottom()
  }
}

// 组件挂载时设置默认欢迎消息
onMounted(() => {
  messages.value.push({
    role: 'assistant',
    content: '你好！我是 AI 编程小助手，可以帮助你解答编程学习和求职面试相关的问题。有什么我可以帮助你的吗？',
    time: getCurrentTime()
  })

  // 自动聚焦输入框
  inputRef.value?.focus()
})
</script>

<style scoped>
.chat-container {
  width: 100%;
  max-width: 900px;
  height: 100vh;
  background: #fff;
  display: flex;
  flex-direction: column;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

.chat-header {
  padding: 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chat-header h1 {
  font-size: 20px;
  font-weight: 600;
}

.chat-id {
  font-size: 12px;
  opacity: 0.8;
  background: rgba(255, 255, 255, 0.2);
  padding: 4px 12px;
  border-radius: 12px;
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 20px;
  background: #f8f9fa;
}

.message {
  display: flex;
  margin-bottom: 20px;
  animation: fadeIn 0.3s ease;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.message-ai {
  flex-direction: row;
}

.message-user {
  flex-direction: row-reverse;
}

.message-avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px;
  font-weight: 600;
  flex-shrink: 0;
}

.message-ai .message-avatar {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  margin-right: 12px;
}

.message-user .message-avatar {
  background: #48bb78;
  color: white;
  margin-left: 12px;
}

.message-content {
  max-width: 70%;
}

.message-text {
  padding: 12px 16px;
  border-radius: 12px;
  line-height: 1.6;
  word-wrap: break-word;
}

.message-ai .message-text {
  background: white;
  border: 1px solid #e2e8f0;
  border-top-left-radius: 4px;
}

.message-user .message-text {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border-top-right-radius: 4px;
}

.message-text.streaming {
  border: 1px dashed #667eea;
}

.message-text .cursor {
  animation: blink 1s infinite;
}

@keyframes blink {
  0%, 50% { opacity: 1; }
  51%, 100% { opacity: 0; }
}

.message-text :deep(pre) {
  background: #2d3748;
  color: #e2e8f0;
  padding: 12px;
  border-radius: 8px;
  overflow-x: auto;
  margin: 8px 0;
}

.message-text :deep(code) {
  background: #edf2f7;
  padding: 2px 6px;
  border-radius: 4px;
  font-family: 'Monaco', 'Menlo', monospace;
  font-size: 14px;
}

.message-user .message-text :deep(code) {
  background: rgba(255, 255, 255, 0.2);
  color: white;
}

.message-time {
  font-size: 11px;
  color: #a0aec0;
  margin-top: 4px;
}

.message-user .message-time {
  text-align: right;
}

.chat-input {
  padding: 20px;
  background: white;
  border-top: 1px solid #e2e8f0;
  display: flex;
  gap: 12px;
}

.chat-input textarea {
  flex: 1;
  padding: 12px 16px;
  border: 2px solid #e2e8f0;
  border-radius: 24px;
  outline: none;
  resize: none;
  font-size: 14px;
  font-family: inherit;
  transition: border-color 0.2s;
  max-height: 120px;
}

.chat-input textarea:focus {
  border-color: #667eea;
}

.chat-input textarea:disabled {
  background: #f7fafc;
  cursor: not-allowed;
}

.chat-input button {
  padding: 12px 28px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border: none;
  border-radius: 24px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: transform 0.1s, opacity 0.2s;
}

.chat-input button:hover:not(:disabled) {
  transform: scale(1.02);
}

.chat-input button:active:not(:disabled) {
  transform: scale(0.98);
}

.chat-input button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>
