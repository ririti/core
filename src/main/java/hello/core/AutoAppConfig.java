package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration//설정정보를 뜻함
@ComponentScan(//@ComponentAnnotaion을 찾아가서 다 빈에 등록한다
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type =  FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
/*

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return  new MemoryMemberRepository();
    }
*/

}
