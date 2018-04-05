-injars      build/release/Vetris.jar
-outjars     build/release/OptiVetris.jar

-libraryjars <java.home>/lib/rt.jar
-libraryjars <java.home>/lib/ext/jfxrt.jar


-optimizationpasses 10
-overloadaggressively 
-repackageclasses ''
-allowaccessmodification

-keepclasseswithmembers public class * { 
    public static void main(java.lang.String[]); 
}
