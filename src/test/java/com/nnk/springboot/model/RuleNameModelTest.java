package com.nnk.springboot.model;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RuleNameModelTest {

    @Test
    public void testHashCode()
    {
        RuleName rule = new RuleName();
        rule.setId(1);
        rule.setName("name");
        rule.setJson("json");
        rule.setDescription("description");
        rule.setSql("sql");
        rule.setTemplate("template");
        rule.setSqlPart("sqlPart");

        assertNotNull(rule.hashCode());

    }

    @Test
    public void testEquals()
    {
        RuleName rule = new RuleName();
        rule.setId(1);
        rule.setName("name");
        rule.setJson("json");
        rule.setDescription("description");
        rule.setSql("sql");
        rule.setTemplate("template");
        rule.setSqlPart("sqlPart");

        RuleName rule2 = new RuleName();
        rule2.setId(1);
        rule2.setName("name");
        rule2.setJson("json");
        rule2.setDescription("description");
        rule2.setSql("sql");
        rule2.setTemplate("template");
        rule2.setSqlPart("sqlPart");

        RuleName rule3 = new RuleName();
        rule3.setId(1);
        rule3.setName("nameRule3");
        rule3.setJson("json");
        rule3.setDescription("description");
        rule3.setSql("sql");
        rule3.setTemplate("template");
        rule3.setSqlPart("sqlPart");


        assertTrue(rule.equals(rule));

        assertTrue(rule.equals(rule2));

        rule2 = null;

        assertFalse(rule.equals(rule2));

        assertFalse((new RuleName().equals(rule)));

        assertFalse(rule.equals(rule3));

        rule3.setName("name");

        rule3.setSqlPart("sqlPartRule3");

        assertFalse(rule.equals(rule3));
    }

    @Test
    public void testToString()
    {
        RuleName rule3 = new RuleName();
        rule3.setId(1);
        rule3.setName("nameRule3");
        rule3.setJson("json");
        rule3.setDescription("description");
        rule3.setSql("sql");
        rule3.setTemplate("template");
        rule3.setSqlPart("sqlPart");

        assertNotNull(rule3.toString());
    }
}
