package me.ubmagh.springmulticonnectorms.web.Soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    // bean definitions

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soap-ws/*");
    }

    @Bean(name = "accounts")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema accountsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AccountsPort");
        wsdl11Definition.setLocationUri("/soap-ws");
        wsdl11Definition.setTargetNamespace("http://ubmagh.me/xml/account");
        wsdl11Definition.setSchema(accountsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema accountsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("accounts.xsd"));
    }

}