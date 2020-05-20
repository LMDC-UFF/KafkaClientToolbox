package br.uff.kafka;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.setProperty("java.security.krb5.conf", "C:\\Users\\C88J\\UFF\\Chave\\krb5.conf");
//        System.getenv().put("KAFKA_KERBEROS_KEYTAB", "/home/lmdc/kerberos/kafka.keytab");
//        System.getenv().put("KAFKA_KERBEROS_USERNAME", "kafka/labesi.lmdc.uff.br@LMDC");
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Main.class)
                .web(WebApplicationType.NONE)
                .run(args);
        KafkaApplication kaf = ctx.getBean(KafkaApplication.class);

        /*  ----- */
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Digite uma msg");
            String msg = scanner.nextLine();
            kaf.send(msg);
        }
    }

}
