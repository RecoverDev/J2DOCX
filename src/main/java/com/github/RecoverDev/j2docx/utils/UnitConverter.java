package com.github.RecoverDev.j2docx.utils;

/** 
 * Вспомогательный класс для преобразования единиц измерения, 
 * используемых в документах DOCX и формате OpenXML.  
 * <p>Поддерживает преобразование между миллиметрами, сантиметрами, 
 * дюймами, пунктами, твипами и English Metric Units (EMU).</p> 
 */ 
 public final class UnitConverter { 
 
    /** 
     * Количество миллиметров в одном дюйме. 
     */ 
    public static final double MILLIMETERS_PER_INCH = 25.4; 
    
    /**
     * Количество сантиметров в одном дюйме. 
     */ 
    public static final double CENTIMETERS_PER_INCH = 2.54; 
    
    /**
     * Количество пунктов в одном дюйме. 
     */ 
    public static final double POINTS_PER_INCH = 72.0; 
    
    /** 
     * Количество твипов в одном пункте. 
     */ 
    public static final int TWIPS_PER_POINT = 20; 
    
    /**
     * Количество твипов в одном дюйме. 
     */
    public static final int TWIPS_PER_INCH = (int) (POINTS_PER_INCH * TWIPS_PER_POINT); 
    
    /**
     * Количество EMU в одном дюйме.
     */ 
    public static final long EMUS_PER_INCH = 914_400L; 
    
    /**
     * Количество EMU в одном сантиметре.
     */ 
    public static final long EMUS_PER_CENTIMETER = 360_000L; 
    
    /**
     * Количество EMU в одном пункте. 
     */ 
    public static final long EMUS_PER_POINT = 12_700L; 
    
    private UnitConverter() { } 
    
    /** 
     * Преобразует миллиметры в твипы. 
     * @param millimeters значение в миллиметрах 
     * @return значение в твипах 
     */ 
    public static int millimetersToTwips(double millimeters) { 
        return (int) Math.round( millimeters * TWIPS_PER_INCH / MILLIMETERS_PER_INCH ); 
    } 
    
    /**
     * Преобразует сантиметры в твипы. 
     * @param centimeters значение в сантиметрах 
     * @return значение в твипах 
     */ 
    public static int centimetersToTwips(double centimeters) { 
        return millimetersToTwips(centimeters * 10); 
    } 
    
    /** 
     * Преобразует дюймы в твипы. 
     * @param inches значение в дюймах 
     * @return значение в твипах 
     */ 
    public static int inchesToTwips(double inches) { 
        return (int) Math.round(inches * TWIPS_PER_INCH); 
    } 
    
    /**
     * Преобразует пункты в твипы. 
     * @param points значение в пунктах 
     * @return значение в твипах 
     */ 
    public static int pointsToTwips(double points) { 
        return (int) Math.round(points * TWIPS_PER_POINT); 
    } 
    
    /** 
     * Преобразует твипы в миллиметры. 
     * @param twips значение в твипах 
     * @return значение в миллиметрах 
     */ 
    public static double twipsToMillimeters(int twips) { 
        return twips * MILLIMETERS_PER_INCH / TWIPS_PER_INCH; 
    } 
    
    /** 
     * Преобразует твипы в сантиметры. 
     * @param twips значение в твипах 
     * @return значение в сантиметрах 
     */ 
    public static double twipsToCentimeters(int twips) { 
        return twipsToMillimeters(twips) / 10; 
    } 
    
    /** 
     * Преобразует твипы в дюймы. 
     * @param twips значение в твипах 
     * @return значение в дюймах 
     */ 
    public static double twipsToInches(int twips) { 
        return (double) twips / TWIPS_PER_INCH; 
    } 
    
    /** 
     * Преобразует твипы в пункты. 
     * @param twips значение в твипах 
     * @return значение в пунктах 
     */ 
    public static double twipsToPoints(int twips) { 
        return (double) twips / TWIPS_PER_POINT; 
    } 
    
    /**
     * Преобразует миллиметры в EMU. 
     * @param millimeters значение в миллиметрах 
     * @return значение в EMU 
     */ 
    public static long millimetersToEmus(double millimeters) { 
        return Math.round( millimeters * EMUS_PER_INCH / MILLIMETERS_PER_INCH ); 
    } 
    
    /** 
     * Преобразует сантиметры в EMU. 
     * @param centimeters значение в сантиметрах 
     * @return значение в EMU 
     */ 
    public static long centimetersToEmus(double centimeters) { 
        return Math.round(centimeters * EMUS_PER_CENTIMETER); 
    } 
    
    /**
     * Преобразует дюймы в EMU. 
     * @param inches значение в дюймах 
     * @return значение в EMU 
     */ 
    public static long inchesToEmus(double inches) { 
        return Math.round(inches * EMUS_PER_INCH); 
    } 
    
    /** 
     * Преобразует пункты в EMU. 
     * @param points значение в пунктах 
     * @return значение в EMU 
     */ 
    public static long pointsToEmus(double points) { 
        return Math.round(points * EMUS_PER_POINT); 
    } 
    
    /**
     * Преобразует EMU в миллиметры. 
     * @param emus значение в EMU 
     * @return значение в миллиметрах 
     */ 
    public static double emusToMillimeters(long emus) { 
        return emus * MILLIMETERS_PER_INCH / EMUS_PER_INCH; 
    } 
    
    /**
     * Преобразует EMU в сантиметры. 
     * @param emus значение в EMU 
     * @return значение в сантиметрах 
     */ 
    public static double emusToCentimeters(long emus) { 
        return emusToMillimeters(emus) / 10; 
    } 
    
    /**
     * Преобразует EMU в дюймы. 
     * @param emus значение в EMU 
     * @return значение в дюймах 
     */ 
    public static double emusToInches(long emus) { 
        return (double) emus / EMUS_PER_INCH; 
    } 
    
    /**
     * Преобразует EMU в пункты. 
     * @param emus значение в EMU 
     * @return значение в пунктах 
     */ 
    public static double emusToPoints(long emus) { 
        return (double) emus / EMUS_PER_POINT; 
    }

}
