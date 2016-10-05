package com.nimblemodule.spring.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nimblemodule.spring.model.Person;
import com.nimblemodule.spring.service.PersonService;

public class PersonServiceImplTest
{
  @Test
public void  testPersonServiceImplAdd(){
    
    
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
    
    PersonService s=  (PersonService) ctx.getBean("personService");
    Person p=new Person();
    p.setCountry("Nepal");
    p.setName("nimble");
   s.addPerson(p);
   Person pp=s.getPersonByName("nimble");
   Assert.assertEquals(pp.getName(),p.getName());
  
    
    
    
  }
}
