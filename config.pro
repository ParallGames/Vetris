-injars      build/libs/Vetris-1.3.0.jar 
-outjars     build/libs/OptiVetris-1.3.0.jar

-libraryjars <java.home>/lib/rt.jar
-libraryjars <java.home>/lib/ext/jfxrt.jar


-optimizationpasses 1000 
-overloadaggressively 
-repackageclasses ''
-allowaccessmodification

-keepclasseswithmembers public class * { 
    public static void main(java.lang.String[]); 
}
