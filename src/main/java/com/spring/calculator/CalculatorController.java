package com.spring.calculator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

//  WEB kontroleris. Pažymi MVC valdiklį. Leidžia viduje naudoti @RequestMapping
//  @RestController anotacija nurodo, jog String tipo rezultatas turėtų būti išspausdinamas klientui toks koks yra.
@RestController
//  žymi konfigūracijos komponentą. Viduje leidžia kurti bean per metodus su @Bean
//  Ši klasės lygio anotacija nurodo Spring karkasui “atspėti” konfigūraciją,
//  remiantis priklausomybėmis (jar bibliotekos), kurias programuotojas įtraukė į projektą.
//  Šiuo atveju ji veikia kartu su main metodu.
@EnableAutoConfiguration
public class CalculatorController {
    // Maršrutizavimo informacija. Šiuo atveju, ji nurodo Spring karkasui,
    // jog visas HTTP užklausas, kurių kelias yra “/” apdoros metodas “home”.
    @RequestMapping(method = RequestMethod.GET, value = "/")
    String hello() {
        // ApplicationContext yra interfeisas, skirtas suteikti informaciją apie aplikacijos konfigūraciją
        // Šiuo atveju naudojama konfigūracija paimama iš XML failo
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // Bean panaudojimas
        HelloWorld bean = (HelloWorld) context.getBean("helloWorld");
        return bean.getHello();
        /*return "Internetinis skaičiuotuvas <p>" +
                "Galimos operacijos: <br>" +
                "&nbsp; &nbsp;  sudėti <br>" +
                "&nbsp; &nbsp;  atimti <br>" +
                "&nbsp; &nbsp;  dauginti <br>" +
                "&nbsp; &nbsp;  dalinti <br>";*/
    }

    // url pavyzdys http://localhost:8080/skaiciuoti?sk1=3&sk2=1&zenklas=%2B
    // specialiems symboliams siųsti per url:
    // https://meyerweb.com/eric/tools/dencoder/
    @RequestMapping(method = RequestMethod.GET, value = "/skaiciuoti")
    String skaiciuoti(@RequestParam HashMap<String, String> skaiciai) {
        int sk1 = Integer.parseInt(skaiciai.get("sk1"));
        int sk2 = Integer.parseInt(skaiciai.get("sk2"));
        String zenklas = skaiciai.get("zenklas");

        int rezultatas = 0;
        if(zenklas.equals("+")) {
            rezultatas = sk1 + sk2;
        } else if (zenklas.equals("-")) {
            rezultatas = sk1 - sk2;
        } else if (zenklas.equals("*")) {
            rezultatas = sk1 * sk2;
        } else if (zenklas.equals("/")) {
            rezultatas = sk1 / sk2;
        }

        return  sk1 + zenklas + sk2 + " = " + rezultatas;
    }
}
