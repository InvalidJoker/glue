@file:Suppress("unused")
package de.joker.glue.text

import java.lang.reflect.Field
import java.util.*
import java.util.stream.Collectors
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.format.TextColor

object Kolors {
    // Decorations
    val MAGIC: TextDecoration = TextDecoration.OBFUSCATED
    val BOLD: TextDecoration = TextDecoration.BOLD
    val STRIKETHROUGH: TextDecoration = TextDecoration.STRIKETHROUGH
    val UNDERLINE: TextDecoration = TextDecoration.UNDERLINED
    val ITALIC: TextDecoration = TextDecoration.ITALIC

    // Bukkit Colors
    val DARKBLUE: TextColor = TextColor.color(0, 0, 170)
    val DARKGREEN: TextColor = TextColor.color(0, 170, 0)
    val DARKAQUA: TextColor = TextColor.color(0, 170, 170)
    val DARKRED: TextColor = TextColor.color(170, 0, 0)
    val DARKPURPLE: TextColor = TextColor.color(170, 0, 170)
    val GOLD: TextColor = TextColor.color(255, 170, 0)
    val GRAY: TextColor = TextColor.color(170, 170, 170)
    val DARKGRAY: TextColor = TextColor.color(85, 85, 85)
    val BLUE: TextColor = TextColor.color(85, 85, 255)
    val GREEN: TextColor = TextColor.color(85, 255, 85)
    val AQUA: TextColor = TextColor.color(85, 255, 255)
    val RED: TextColor = TextColor.color(255, 85, 85)
    val LIGHTPURPLE: TextColor = TextColor.color(255, 85, 255)
    val YELLOW: TextColor = TextColor.color(255, 255, 85)
    val WHITE: TextColor = TextColor.color(255, 255, 255)

    // Additional Colors
    val ALICEBLUE: TextColor = TextColor.color(240, 248, 255)
    val ANTIQUEWHITE: TextColor = TextColor.color(250, 235, 215)
    val AQUAMARINE: TextColor = TextColor.color(127, 255, 212)
    val AZURE: TextColor = TextColor.color(240, 255, 255)
    val BEIGE: TextColor = TextColor.color(245, 245, 220)
    val BISQUE: TextColor = TextColor.color(255, 228, 196)
    val BLACK: TextColor = TextColor.color(0, 0, 0)
    val BLANCHEDALMOND: TextColor = TextColor.color(255, 235, 205)
    val BLUEVIOLET: TextColor = TextColor.color(138, 43, 226)
    val BROWN: TextColor = TextColor.color(165, 42, 42)
    val BURLYWOOD: TextColor = TextColor.color(222, 184, 135)
    val CADETBLUE: TextColor = TextColor.color(95, 158, 160)
    val CHARTREUSE: TextColor = TextColor.color(127, 255, 0)
    val CHOCOLATE: TextColor = TextColor.color(210, 105, 30)
    val CORAL: TextColor = TextColor.color(255, 127, 80)
    val CORNFLOWERBLUE: TextColor = TextColor.color(100, 149, 237)
    val CORNSILK: TextColor = TextColor.color(255, 248, 220)
    val CRIMSON: TextColor = TextColor.color(220, 20, 60)
    val CYAN: TextColor = TextColor.color(0, 255, 255)
    val DARKCYAN: TextColor = TextColor.color(0, 139, 139)
    val DARKGOLDENROD: TextColor = TextColor.color(184, 134, 11)
    val DARKKHAKI: TextColor = TextColor.color(189, 183, 107)
    val DARKMAGENTA: TextColor = TextColor.color(139, 0, 139)
    val DARKOLIVEGREEN: TextColor = TextColor.color(85, 107, 47)
    val DARKORANGE: TextColor = TextColor.color(255, 140, 0)
    val DARKORCHID: TextColor = TextColor.color(153, 50, 204)
    val DARKSALMON: TextColor = TextColor.color(233, 150, 122)
    val DARKSEAGREEN: TextColor = TextColor.color(143, 188, 143)
    val DARKSLATEBLUE: TextColor = TextColor.color(72, 61, 139)
    val DARKSLATEGRAY: TextColor = TextColor.color(47, 79, 79)
    val DARKTURQUOISE: TextColor = TextColor.color(0, 206, 209)
    val DARKVIOLET: TextColor = TextColor.color(148, 0, 211)
    val DEEPPINK: TextColor = TextColor.color(255, 20, 147)
    val DEEPSKYBLUE: TextColor = TextColor.color(0, 191, 255)
    val DIMGRAY: TextColor = TextColor.color(105, 105, 105)
    val DODGERBLUE: TextColor = TextColor.color(30, 144, 255)
    val FIREBRICK: TextColor = TextColor.color(178, 34, 34)
    val FLORALWHITE: TextColor = TextColor.color(255, 250, 240)
    val FORESTGREEN: TextColor = TextColor.color(34, 139, 34)
    val FUCHSIA: TextColor = TextColor.color(255, 0, 255)
    val GAINSBORO: TextColor = TextColor.color(220, 220, 220)
    val GHOSTWHITE: TextColor = TextColor.color(248, 248, 255)
    val GOLDENROD: TextColor = TextColor.color(218, 165, 32)
    val GREENYELLOW: TextColor = TextColor.color(173, 255, 47)
    val HONEYDEW: TextColor = TextColor.color(240, 255, 240)
    val HOTPINK: TextColor = TextColor.color(255, 105, 180)
    val INDIANRED: TextColor = TextColor.color(205, 92, 92)
    val INDIGO: TextColor = TextColor.color(75, 0, 130)
    val IVORY: TextColor = TextColor.color(255, 255, 240)
    val KHAKI: TextColor = TextColor.color(240, 230, 140)
    val LAVENDER: TextColor = TextColor.color(230, 230, 250)
    val LAVENDERBLUSH: TextColor = TextColor.color(255, 240, 245)
    val LAWNGREEN: TextColor = TextColor.color(124, 252, 0)
    val LEMONCHIFFON: TextColor = TextColor.color(255, 250, 205)
    val LIGHTBLUE: TextColor = TextColor.color(173, 216, 230)
    val LIGHTCORAL: TextColor = TextColor.color(240, 128, 128)
    val LIGHTCYAN: TextColor = TextColor.color(224, 255, 255)
    val LIGHTGOLDENRODYELLOW: TextColor = TextColor.color(250, 250, 210)
    val LIGHTGRAY: TextColor = TextColor.color(211, 211, 211)
    val LIGHTGREEN: TextColor = TextColor.color(144, 238, 144)
    val LIGHTPINK: TextColor = TextColor.color(255, 182, 193)
    val LIGHTSALMON: TextColor = TextColor.color(255, 160, 122)
    val LIGHTSEAGREEN: TextColor = TextColor.color(32, 178, 170)
    val LIGHTSKYBLUE: TextColor = TextColor.color(135, 206, 250)
    val LIGHTSLATEGRAY: TextColor = TextColor.color(119, 136, 153)
    val LIGHTSTEELBLUE: TextColor = TextColor.color(176, 196, 222)
    val LIGHTYELLOW: TextColor = TextColor.color(255, 255, 224)
    val LIME: TextColor = TextColor.color(0, 255, 0)
    val LIMEGREEN: TextColor = TextColor.color(50, 205, 50)
    val LINEN: TextColor = TextColor.color(250, 240, 230)
    val MAGENTA: TextColor = TextColor.color(255, 0, 255)
    val MAROON: TextColor = TextColor.color(128, 0, 0)
    val MEDIUMAQUAMARINE: TextColor = TextColor.color(102, 205, 170)
    val MEDIUMBLUE: TextColor = TextColor.color(0, 0, 205)
    val MEDIUMORCHID: TextColor = TextColor.color(186, 85, 211)
    val MEDIUMPURPLE: TextColor = TextColor.color(147, 112, 219)
    val MEDIUMSEAGREEN: TextColor = TextColor.color(60, 179, 113)
    val MEDIUMSLATEBLUE: TextColor = TextColor.color(123, 104, 238)
    val MEDIUMSPRINGGREEN: TextColor = TextColor.color(0, 250, 154)
    val MEDIUMTURQUOISE: TextColor = TextColor.color(72, 209, 204)
    val MEDIUMVIOLETRED: TextColor = TextColor.color(199, 21, 133)
    val MIDNIGHTBLUE: TextColor = TextColor.color(25, 25, 112)
    val MINTCREAM: TextColor = TextColor.color(245, 255, 250)
    val MISTYROSE: TextColor = TextColor.color(255, 228, 225)
    val MOCCASIN: TextColor = TextColor.color(255, 228, 181)
    val NAVAJOWHITE: TextColor = TextColor.color(255, 222, 173)
    val NAVY: TextColor = TextColor.color(0, 0, 128)
    val OLDLACE: TextColor = TextColor.color(253, 245, 230)
    val OLIVE: TextColor = TextColor.color(128, 128, 0)
    val OLIVEDRAB: TextColor = TextColor.color(107, 142, 35)
    val ORANGE: TextColor = TextColor.color(255, 165, 0)
    val ORANGERED: TextColor = TextColor.color(255, 69, 0)
    val ORCHID: TextColor = TextColor.color(218, 112, 214)
    val PALEGOLDENROD: TextColor = TextColor.color(238, 232, 170)
    val PALEGREEN: TextColor = TextColor.color(152, 251, 152)
    val PALETURQUOISE: TextColor = TextColor.color(175, 238, 238)
    val PALEVIOLETRED: TextColor = TextColor.color(219, 112, 147)
    val PAPAYAWHIP: TextColor = TextColor.color(255, 239, 213)
    val PEACHPUFF: TextColor = TextColor.color(255, 218, 185)
    val PERU: TextColor = TextColor.color(205, 133, 63)
    val PINK: TextColor = TextColor.color(255, 192, 203)
    val PLUM: TextColor = TextColor.color(221, 160, 221)
    val POWDERBLUE: TextColor = TextColor.color(176, 224, 230)
    val PURPLE: TextColor = TextColor.color(128, 0, 128)
    val ROSYBROWN: TextColor = TextColor.color(188, 143, 143)
    val ROYALBLUE: TextColor = TextColor.color(65, 105, 225)
    val SADDLEBROWN: TextColor = TextColor.color(139, 69, 19)
    val SALMON: TextColor = TextColor.color(250, 128, 114)
    val SANDYBROWN: TextColor = TextColor.color(244, 164, 96)
    val SEAGREEN: TextColor = TextColor.color(46, 139, 87)
    val SEASHELL: TextColor = TextColor.color(255, 245, 238)
    val SIENNA: TextColor = TextColor.color(160, 82, 45)
    val SILVER: TextColor = TextColor.color(192, 192, 192)
    val SKYBLUE: TextColor = TextColor.color(135, 206, 235)
    val SLATEBLUE: TextColor = TextColor.color(106, 90, 205)
    val SLATEGRAY: TextColor = TextColor.color(112, 128, 144)
    val SNOW: TextColor = TextColor.color(255, 250, 250)
    val SPRINGGREEN: TextColor = TextColor.color(0, 255, 127)
    val STEELBLUE: TextColor = TextColor.color(70, 130, 180)
    val TAN: TextColor = TextColor.color(210, 180, 140)
    val TEAL: TextColor = TextColor.color(0, 128, 128)
    val THISTLE: TextColor = TextColor.color(216, 191, 216)
    val TOMATO: TextColor = TextColor.color(255, 99, 71)
    val TURQUOISE: TextColor = TextColor.color(64, 224, 208)
    val VIOLET: TextColor = TextColor.color(238, 130, 238)
    val WHEAT: TextColor = TextColor.color(245, 222, 179)
    val WHITESMOKE: TextColor = TextColor.color(245, 245, 245)
    val YELLOWGREEN: TextColor = TextColor.color(154, 205, 50)
    val POISONGREEN: TextColor = TextColor.color(0, 247, 0)
    val ALMOND: TextColor = TextColor.color(239, 222, 205)
    val AMARANTH: TextColor = TextColor.color(229, 43, 80)
    val AMARANTHDEEPPURPLE: TextColor = TextColor.color(159, 43, 104)
    val AMARANTHPURPLE: TextColor = TextColor.color(171, 39, 79)
    val ANTIQUERUBY: TextColor = TextColor.color(132, 27, 45)
    val ANTIQUEFUCHSIA: TextColor = TextColor.color(145, 92, 131)
    val AMETHYST: TextColor = TextColor.color(153, 102, 204)
    val BABYBLUE: TextColor = TextColor.color(137, 207, 240)
    val BABYPINK: TextColor = TextColor.color(244, 194, 194)
    val BLACKBEAN: TextColor = TextColor.color(61, 12, 2)
    val BLOODRED: TextColor = TextColor.color(102, 0, 0)
    val BONE: TextColor = TextColor.color(227, 218, 201)
    val BRONZE: TextColor = TextColor.color(205, 127, 50)
    val CARDINAL: TextColor = TextColor.color(196, 30, 58)
    val CHERRYBLOSSOMPINK: TextColor = TextColor.color(255, 183, 197)
    val CHILLIRED: TextColor = TextColor.color(226, 61, 40)
    val CHINAPINK: TextColor = TextColor.color(222, 111, 161)
    val COPPER: TextColor = TextColor.color(184, 115, 51)
    val COTTONCANDY: TextColor = TextColor.color(255, 188, 217)
    val CREAM: TextColor = TextColor.color(255, 253, 208)
    val EGGSHELL: TextColor = TextColor.color(240, 234, 214)
    val EGGPLANT: TextColor = TextColor.color(97, 64, 81)
    val EMERALD: TextColor = TextColor.color(80, 200, 120)
    val FRENCHBEIGE: TextColor = TextColor.color(166, 123, 91)
    val FUZZYWUZZY: TextColor = TextColor.color(204, 102, 102)
    val KOBI: TextColor = TextColor.color(231, 159, 196)
    val LAVA: TextColor = TextColor.color(207, 16, 32)
    val MAHOGANY: TextColor = TextColor.color(192, 64, 0)
    val MANGO: TextColor = TextColor.color(253, 190, 2)
    val MANGOTANGO: TextColor = TextColor.color(255, 130, 67)
    val MAXIMUMBLUE: TextColor = TextColor.color(71, 171, 204)
    val MAXIMUMGREEN: TextColor = TextColor.color(94, 140, 49)
    val MAXIMUMRED: TextColor = TextColor.color(217, 33, 33)
    val MAXIMUMPURPLE: TextColor = TextColor.color(115, 51, 128)
    val MAXIMUMYELLOW: TextColor = TextColor.color(250, 250, 55)
    val MINTGREEN: TextColor = TextColor.color(152, 255, 152)
    val OCHRE: TextColor = TextColor.color(204, 119, 34)
    val OXBLOOD: TextColor = TextColor.color(128, 0, 32)
    val OXFORDBLUE: TextColor = TextColor.color(0, 33, 71)
    val PEACH: TextColor = TextColor.color(255, 229, 180)
    val RICHBLACK: TextColor = TextColor.color(1, 11, 19)
    val ROYALPURPLE: TextColor = TextColor.color(120, 81, 169)
    val RUBY: TextColor = TextColor.color(224, 17, 95)
    val STRAWBERRY: TextColor = TextColor.color(252, 90, 141)
    val SUNGLOW: TextColor = TextColor.color(255, 204, 51)
    val SUNSET: TextColor = TextColor.color(250, 214, 165)
    val TEAGREEN: TextColor = TextColor.color(208, 240, 192)
    val UNBLEACHEDSILK: TextColor = TextColor.color(255, 221, 202)
    val UNBLUE: TextColor = TextColor.color(91, 146, 229)
    val VANILLA: TextColor = TextColor.color(243, 229, 171)
    val WINE: TextColor = TextColor.color(114, 47, 55)


    /**
     * Returns all [TextColor]s in this Class
     *
     * @return a List of all Colors in this Class
     */
    fun allColors(): MutableList<TextColor?> {
        return Arrays.stream(Kolors::class.java.getFields())
            .filter { field: Field? -> field!!.type == TextColor::class.java }.map<TextColor?> { field: Field? ->
                try {
                    return@map field!!.get(null) as TextColor?
                } catch (ignored: IllegalArgumentException) {
                    return@map null
                } catch (ignored: IllegalAccessException) {
                    return@map null
                }
            }.filter { obj: TextColor? -> Objects.nonNull(obj) }.collect(Collectors.toList())
    }

    /**
     * Returns all [TextDecoration]s in this class
     *
     * @return a list of all Decorations in this class
     */
    fun allDecorations(): MutableList<TextDecoration?> {
        return Arrays.stream(Kolors::class.java.getFields())
            .filter { field: Field? -> field!!.type == TextDecoration::class.java }
            .map<TextDecoration?> { field: Field? ->
                try {
                    return@map field!!.get(null) as TextDecoration?
                } catch (ignored: IllegalArgumentException) {
                    return@map null
                } catch (ignored: IllegalAccessException) {
                    return@map null
                }
            }.filter { obj: TextDecoration? -> Objects.nonNull(obj) }.collect(Collectors.toList())
    }
}