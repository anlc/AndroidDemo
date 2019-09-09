package com.example.butter;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.sun.tools.javac.code.Symbol;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-09-08
 */
final class Id {

    private static final ClassName ANDROID_R = ClassName.get("android", "R");
    private static final String R = "R";

    final int value;
    final CodeBlock code;
    final boolean qualifed;

    Id(int value) {
        this(value, null);
    }

    Id(int value, Symbol rSymbol) {
        this.value = value;
        if (rSymbol != null) {
            String qualifiedName = rSymbol.packge().getQualifiedName().toString();
            String name = rSymbol.enclClass().name.toString();
            ClassName className = ClassName.get(qualifiedName, R, name);

            String resourceName = rSymbol.name.toString();
            ClassName topName = className.topLevelClassName();
            this.code = topName.equals(ANDROID_R)
                    ? CodeBlock.of("$L.$N", className, resourceName)
                    : CodeBlock.of("$T.$N", className, resourceName);

            System.out.println(" qualifiedName:" + qualifiedName +
                    "   resourceName: " + resourceName +
                    "   topName: " + topName);
            this.qualifed = true;
        } else {
            this.code = CodeBlock.of("$L", value);
            this.qualifed = false;
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Id && value == ((Id) o).value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Please use value or code explicitly");
    }
}
