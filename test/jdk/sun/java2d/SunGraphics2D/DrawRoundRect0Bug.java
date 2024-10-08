/*
 * Copyright (c) 2001, 2024, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 4515761
 * @summary verify that drawRoundRect produces correct output for 0 w/h
 */

import java.awt.Color;
import static java.awt.Color.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class DrawRoundRect0Bug {

    public static void main(String argv[]) {
        BufferedImage img = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();

        g.setColor(white);
        g.fillRect(0, 0, img.getWidth(), img.getHeight());

        g.setColor(green);
        g.drawLine(150, 90, 150, 110);
        if (img.getRGB(150, 100) != green.getRGB()) {
            throw new RuntimeException("Vertical line not green");
        }

        g.setColor(blue);
        g.drawRoundRect(160, 90, 0, 20, 4, 4);
        if (img.getRGB(160, 100) != blue.getRGB()) {
            throw new RuntimeException("Vertical (ie zero width) round rect not blue");
        }

        g.setColor(green);
        g.drawLine(150, 140, 170, 140);
        if (img.getRGB(160, 140) != green.getRGB()) {
            throw new RuntimeException("Horizontal line not green");
        }

        g.setColor(blue);
        g.drawRoundRect(150, 150, 20, 0, 4, 4);
        if (img.getRGB(160, 150) != blue.getRGB()) {
            throw new RuntimeException("Horizontal (ie zero height) round rect not blue");
        }
    }

}
