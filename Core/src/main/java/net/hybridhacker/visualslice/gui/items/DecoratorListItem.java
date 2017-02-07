package net.hybridhacker.visualslice.gui.items;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Flaflo
 */
@RequiredArgsConstructor
public final class DecoratorListItem {

    private final String text;

    @Getter
    @Setter
    private boolean selected;
    
    @Getter
    private final boolean configurable;

    @Override
    public String toString() {
        return text;
    }
}
