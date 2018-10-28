package com.example.webflow.thymeleaf;

import com.example.webflow.annotations.FormField;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class FieldAttributeTagProcessor extends AbstractAttributeTagProcessor {


    private static final String ATTR_NAME = "field";
    private static final int PRECEDENCE = 10000;

    public FieldAttributeTagProcessor(final String dialectPrefix) {
        super(
                TemplateMode.HTML, // This processor will apply only to HTML mode
                dialectPrefix,     // Prefix to be applied to name for matching
                null,              // No tag name: match any tag name
                false,             // No prefix to be applied to tag name
                ATTR_NAME,         // Name of the attribute that will be matched
                true,              // Apply dialect prefix to attribute name
                PRECEDENCE,        // Precedence (inside dialect's precedence)
                true);             // Remove the matched attribute afterwards
    }

    protected void doProcess(
            final ITemplateContext context, final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementTagStructureHandler structureHandler) {

        final IEngineConfiguration configuration = context.getConfiguration();

        final IStandardExpressionParser parser =
                StandardExpressions.getExpressionParser(configuration);

        final IStandardExpression expression = parser.parseExpression(context, attributeValue);

//        final String planet = (String) expression.execute(context);


        String fieldName = attributeValue.split("\\.")[1].replace("}", "");
        try {
            FormField annotation = context.getVariable("user").getClass().getDeclaredField(fieldName).getAnnotation(FormField.class);
            if (annotation != null) {
                String body = "";
                if ("text".equals(annotation.type())) {

                    body = "<input type=\"" + annotation.type() + "\" name = \"" + fieldName + "\">";
                } else {
                    body = generateSelect(annotation);
                }

                structureHandler.setBody(body, false);
            }
        } catch (NoSuchFieldException e) {
            log.error("Error", e);
        }

    }

    private String generateSelect(FormField annotation) {
        String options = Stream.of(annotation.values())
                .map(val -> "<option value=\"" + val + "\">" + val + "</option>")
                .collect(Collectors.joining());
        String body = "<select>\n" +
                options +
                "</select>";
        return body;
    }
}
