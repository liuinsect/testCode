package fastjson;

import com.alibaba.fastjson.JSON;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-11
 * Time: 上午11:10
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        Person person = new Person();
        person.setId("1");
        person.setName("json");

        String json = JSON.toJSONString(person);
        Person newPerson = com.alibaba.fastjson.JSON.parseObject(json,Person.class);


        System.out.println(newPerson.getId());
        System.out.println(newPerson.getName());



    }

}
