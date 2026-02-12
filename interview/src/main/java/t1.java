public class t1 {

    // 自定义异常类，区分不同的错误类型
    public static class NumberParseException extends RuntimeException {
        private final ErrorType errorType;

        public NumberParseException(ErrorType errorType, String message) {
            super(message);
            this.errorType = errorType;
        }

        public ErrorType getErrorType() {
            return errorType;
        }
    }

    public enum ErrorType {
        NULL_INPUT("输入为空"),
        EMPTY_INPUT("输入字符串为空"),
        INVALID_FORMAT("格式不正确"),
        NO_DIGIT("未找到有效数字"),
        OVERFLOW("数值溢出");

        private final String desc;

        ErrorType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public static void main(String[] args) {
        // 测试用例
        String[] testCases = {
            null,
            "",
            "  ",
            "-123",
            "+456",
            "  -789",
            "  +123",
            "abc",
            "-12a3",
            "123abc",
            "--123",
            "++123",
            "-+123",
            "9999999999",
            "-9999999999"
        };

        for (String s : testCases) {
            try {
                Integer ret = func(s);
                System.out.printf("输入: '%s' => 结果: %d%n", s, ret);
            } catch (NumberParseException e) {
                System.out.printf("输入: '%s' => 错误: [%s] %s%n",
                    s, e.getErrorType().getDesc(), e.getMessage());
            } catch (Exception e) {
                System.out.printf("输入: '%s' => 未知错误: %s%n", s, e.getMessage());
            }
        }
    }

    /**
     * 字符串转整数方法
     * @param str 输入字符串
     * @return 转换后的整数
     * @throws NumberParseException 不同类型的解析异常
     */
    public static Integer func(String str) {
        // 1. 空值检查
        if (str == null) {
            throw new NumberParseException(ErrorType.NULL_INPUT, "输入字符串不能为null");
        }

        // 2. 空字符串检查
        if (str.trim().isEmpty()) {
            throw new NumberParseException(ErrorType.EMPTY_INPUT, "输入字符串不能为空");
        }

        // 3. 解析符号和数字开始位置
        boolean isNegative = false;
        int index = 0;
        int signCount = 0;  // 符号计数，防止多个符号

        for (; index < str.length(); index++) {
            char c = str.charAt(index);

            if (c == ' ') {
                // 前导空格跳过
                continue;
            } else if (c == '-' || c == '+') {
                signCount++;
                if (signCount > 1) {
                    throw new NumberParseException(ErrorType.INVALID_FORMAT,
                        "不能有多个符号: " + str);
                }
                isNegative = (c == '-');
            } else if (Character.isDigit(c)) {
                // 找到数字开始位置，停止循环
                break;
            } else {
                throw new NumberParseException(ErrorType.INVALID_FORMAT,
                    "字符 '" + c + "' 不是有效数字: " + str);
            }
        }

        // 4. 检查是否找到数字
        if (index >= str.length() || !Character.isDigit(str.charAt(index))) {
            throw new NumberParseException(ErrorType.NO_DIGIT, "未找到有效数字: " + str);
        }

        // 5. 验证剩余字符都是数字
        for (int i = index; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                throw new NumberParseException(ErrorType.INVALID_FORMAT,
                    "数字后不能包含非数字字符 '" + str.charAt(i) + "': " + str);
            }
        }

        // 6. 转换数字并检查溢出
        long result = 0;  // 使用long防止溢出
        for (int i = index; i < str.length(); i++) {
            int digit = str.charAt(i) - '0';
            result = result * 10 + digit;

            // 检查是否超出整数范围
            if (isNegative && -result < Integer.MIN_VALUE) {
                throw new NumberParseException(ErrorType.OVERFLOW,
                    "数值下溢: " + str + " < " + Integer.MIN_VALUE);
            }
            if (!isNegative && result > Integer.MAX_VALUE) {
                throw new NumberParseException(ErrorType.OVERFLOW,
                    "数值上溢: " + str + " > " + Integer.MAX_VALUE);
            }
        }

        return (int) (isNegative ? -result : result);
    }
}
