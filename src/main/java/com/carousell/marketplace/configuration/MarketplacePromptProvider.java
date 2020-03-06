package com.carousell.marketplace.configuration;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;


/**
 * @author faizanmalik
 * creation date 3/5/20
 */
@Configuration
public class MarketplacePromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("#",
            AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE)
        );
    }
}
