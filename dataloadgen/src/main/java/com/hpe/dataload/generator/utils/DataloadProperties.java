package com.hpe.dataload.generator.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface DataloadProperties {

    /**
     * ChooseInput params configuration
     *
     */
    boolean proceedChoosInputAction = false;
    boolean chooseActionReccomended = true;
    boolean chooseActionPossible = false;
    boolean chooseActionExcluded = false;

    /**
     * Blocks params configuration
     *
     */
    boolean proceedBlocks = true;
    boolean blocksReccomended = true;
    boolean blocksPossible = false;
    boolean blockActionExcluded = false;

    /**
     * You can configure in this list all the parameters that are going to be added to a output list
     *
     *
     */
     Set<String> chooseInputParams = new HashSet<String>() {{
        add("customerId");
        add("orderId");
        add("backup");
    }};


}
