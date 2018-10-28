package com.example.webflow.thymeleaf;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.HashSet;
import java.util.Set;

public class FormDialect extends AbstractProcessorDialect {

    public FormDialect() {
        super(
                "Form Dialect",    // Dialect name
                "form",            // Dialect prefix (hello:*)
                1000);              // Dialect precedence
    }

    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new FieldAttributeTagProcessor(dialectPrefix));
        return processors;
    }
}
