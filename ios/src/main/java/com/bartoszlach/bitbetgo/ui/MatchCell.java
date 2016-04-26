package com.bartoszlach.bitbetgo.ui;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Property;
import com.intel.inde.moe.natj.objc.ann.Selector;

import ios.NSObject;
import ios.uikit.UIButton;
import ios.uikit.UILabel;
import ios.uikit.UITableViewCell;
import ios.uikit.UIViewController;

/**
 * Created by bartoszlach on 26.04.2016.
 */
//@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
//@ObjCClassName("MatchCell")
//@RegisterOnStartup
public class MatchCell extends UITableViewCell {
    protected MatchCell(Pointer peer) {
        super(peer);
    }

//    @Owned
//    @Selector("alloc")
//    public static native MatchCell alloc();
//
//    @Selector("init")
//    public native MatchCell init();
//
//    protected MatchCell(Pointer peer) {
//        super(peer);
//    }

}
