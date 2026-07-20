package com.github.RecoverDev.j2docx;

import com.github.RecoverDev.j2docx.styles.Style;

public interface Stylable<SELF, STYLE extends Style<?>> {

    SELF style(STYLE style);

    STYLE getStyle();

}
