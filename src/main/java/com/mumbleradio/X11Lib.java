package com.mumbleradio;

import com.sun.jna.*;
import com.sun.jna.platform.unix.X11;

public interface X11Lib extends Library {

    X11Lib INSTANCE = Native.load("Xext", X11Lib.class);

    int ShapeInput = 2;
    int ShapeSet = 0;

//    class XRectangle extends Structure {
//        public short x, y;
//        public short width, height;
//        public short x2, y2; // padding
//
//        @Override
//        protected java.util.List<String> getFieldOrder() {
//            return java.util.Arrays.asList("x", "y", "width", "height", "x2", "y2");
//        }
//    }
//
//    void XShapeCombineRectangles(X11.Display display, X11.Window w,
//                                 int dest_kind, int x_off, int y_off,
//                                 XRectangle[] rectangles, int n_rects,
//                                 int op, int ordering);

    @Structure.FieldOrder({"x", "y", "width", "height"})
    class XRectangle extends Structure {
        public short x, y;
        public short width, height;
    }

    void XShapeCombineRectangles(X11.Display display, X11.Window w, int shapeKind, int xOff, int yOff,
                                 XRectangle[] rectangles, int n_rects, int op, int ordering);



}
