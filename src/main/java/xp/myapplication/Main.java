package xp.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


import static de.robv.android.xposed.XposedBridge.*;

/**
 * Created by r on 2017/7/6.
 */

public class Main implements IXposedHookLoadPackage {
    Class<?> hookClass = null;
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if(!loadPackageParam.packageName.equals("cn.EGGMaster"))
            return;
        XposedBridge.log("package:"+loadPackageParam.packageName);
        XposedHelpers.findAndHookMethod("cn.EGGMaster.ui.MainActivity", loadPackageParam.classLoader, "onCheckedChanged", CompoundButton.class,boolean.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                //param.args[1]=false;
                Context context = (Context) param.thisObject;
                Toast.makeText(context,"hook成功,haha",Toast.LENGTH_LONG).show();
                XposedBridge.log("0"+param.args[1]);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                XposedBridge.log("1"+param.args[1]);
            }
    });
        XposedHelpers.findAndHookMethod("cn.EGGMaster.ui.MainActivity", loadPackageParam.classLoader, "onClick", DialogInterface.class, int.class, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {

                return null;
            }
        });

}
}
