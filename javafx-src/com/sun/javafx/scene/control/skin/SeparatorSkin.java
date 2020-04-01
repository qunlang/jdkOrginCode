/*
 * Copyright (c) 2010, 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.sun.javafx.scene.control.skin;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.Region;

import java.util.Collections;
import com.sun.javafx.scene.control.behavior.BehaviorBase;

/**
 *
 */
public class SeparatorSkin extends BehaviorSkinBase<Separator, BehaviorBase<Separator>> {

    /**
     * Separator's have no intrinsic length, so we need to hard code some sort
     * of default preferred size when a separator is not otherwise being resized.
     * This is the default length to use (height when vertical, width when horizontal)
     * for computing the preferred width/height.
     */
    private static final double DEFAULT_LENGTH = 10;

    /**
     * The region to use for rendering the line. The line is specified via
     * CSS. By default we use a single stroke to render the line, but the
     * programmer could use images or whatnot from CSS instead.
     */
    private final Region line;

    /**
     * Create a new SeparatorSkin. Just specify the separator, thanks very much.
     * @param separator not null
     */
    public SeparatorSkin(Separator separator) {
        // There is no behavior for the separator, so we just create a
        // dummy behavior base instead, since SkinBase will complain
        // about it being null.
        super(separator, new BehaviorBase<>(separator, Collections.emptyList()));

        line = new Region();
        line.getStyleClass().setAll("line");

        getChildren().add(line);
        registerChangeListener(separator.orientationProperty(), "ORIENTATION");
        registerChangeListener(separator.halignmentProperty(), "HALIGNMENT");
        registerChangeListener(separator.valignmentProperty(), "VALIGNMENT");
    }

    @Override protected void handleControlPropertyChanged(String p) {
        super.handleControlPropertyChanged(p);
        if ("ORIENTATION".equals(p) || "HALIGNMENT".equals(p) || "VALIGNMENT".equals(p)) {
            getSkinnable().requestLayout();
        }
    }

    /**
     * We only need to deal with the single "line" child region. The important
     * thing here is that we want a horizontal separator to have a line which is
     * as wide as the separator (less the left/right padding), but as thin as
     * it can be (based on its own pref height). The same idea for a vertical
     * separator. It should be as tall as the separator (less the top and
     * bottom padding) but as thin as can be (the pref width of the line).
     * <p>
     * Then position the line within the separator such that the alignment
     * properties are honored.
     */
    @Override protected void layoutChildren(final double x, final double y,
            final double w, final double h) {
        final Separator sep = getSkinnable();

        if (sep.getOrientation() == Orientation.HORIZONTAL) {
            // Resize to the content width, and the pref height of the line.
            line.resize(w, line.prefHeight(-1));
        } else {
            // Resize to the pref width of the line and the content height.
            line.resize(line.prefWidth(-1), h);
        }

        // Now that the line has been sized, simply position it
        positionInArea(line, x, y, w, h, 0, sep.getHalignment(), sep.getValignment());
    }

    @Override protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

    @Override protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    @Override protected double computePrefWidth(double h, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Separator sep = getSkinnable();
        double w = sep.getOrientation() == Orientation.VERTICAL ? line.prefWidth(-1) : DEFAULT_LENGTH;
        return w + leftInset + rightInset;
    }

    @Override protected double computePrefHeight(double w, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Separator sep = getSkinnable();
        double h = sep.getOrientation() == Orientation.VERTICAL ? DEFAULT_LENGTH : line.prefHeight(-1);
        return h + topInset + bottomInset;
    }

    @Override protected double computeMaxWidth(double h, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Separator sep = getSkinnable();
        return sep.getOrientation() == Orientation.VERTICAL ? sep.prefWidth(h) : Double.MAX_VALUE;
    }

    @Override protected double computeMaxHeight(double w, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Separator sep = getSkinnable();
        return sep.getOrientation() == Orientation.VERTICAL ? Double.MAX_VALUE : sep.prefHeight(w);
    }
}
