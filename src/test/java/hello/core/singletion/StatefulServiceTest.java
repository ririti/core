package hello.core.singletion;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void  statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreaA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreaB : B사용자 10000원 주문
        statefulService2.order("userB", 20000);

        //ThreedA: 사용지A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); //20000이 나오는 이유는 A다음 B가 끼어들어 바뀜

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static  class  TestConfig{

        @Bean
        public  StatefulService statefulService(){
            return  new StatefulService();
        }
    }
}
