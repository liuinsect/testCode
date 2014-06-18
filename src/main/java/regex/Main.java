package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-17
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        Pattern pattern = Pattern.compile("\\Quser\\E");
        Matcher matcher = pattern.matcher("user");
        System.out.println(matcher.matches());

    }

}
