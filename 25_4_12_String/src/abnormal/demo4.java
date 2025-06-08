package abnormal;

// 用户名异常类
class NameException extends Exception {
    public NameException(String message) {
        super(message);
    }
}

// 密码异常类
class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
}

public class demo4 {
    private String name = "liren";
    private String password = "123456";

    public void login() throws NameException, PasswordException {
        if (!name.equals("liren")) {
            throw new NameException("用户名错误");
        }
        if (!password.equals("654321")) {
            throw new PasswordException("密码错误");
        }
        System.out.println("登录成功");
    }

    public static void main(String[] args) {
        try {
            demo4 f = new demo4();
            f.login();
        } catch (NameException e) {
            e.printStackTrace();
        } catch (PasswordException e) {
            e.printStackTrace();
        }
    }
}
