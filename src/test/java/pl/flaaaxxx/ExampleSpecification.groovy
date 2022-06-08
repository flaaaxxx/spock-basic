package pl.flaaaxxx

import pl.flaaaxxx.spockbasic.Colour
import pl.flaaaxxx.spockbasic.Palette
import pl.flaaaxxx.spockbasic.Polygon
import pl.flaaaxxx.spockbasic.Renderer
import pl.flaaaxxx.spockbasic.TooFewSidesException
import spock.lang.Ignore
import spock.lang.IgnoreRest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import spock.util.mop.Use


class ExampleSpecification extends Specification {

    def "should be a simple assertion"() {
        expect:
        1 == 1
    }

    def "should demonstrate given-when-then"() {

//        Możesz przeczytać dany/kiedy/wtedy test w ten sposób:
//
//        biorąc pod uwagę, że mam wielokąt z czterema bokami,
//        kiedy otrzymamy liczbę boków
//        wtedy liczba boków powinna być równa czterem

        given:
        def polygon = new Polygon(4)

        when:
        int sides = polygon.numberOfSides

        then:
        sides == 4
    }

    def "should demonstrate given-when-then in one block"() {
        when:
        int sides = new Polygon(4).numberOfSides

        then:
        sides == 4
    }

    def "should expect Exceptions"() {
        when:
        new Polygon(0)

        then:
        thrown(TooFewSidesException)
    }

    def "should expect Exceptions and check with exception numberOfSides"() {
        when:
        new Polygon(0)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == 0
    }

    def "should expect an Exception to be thrown for invalid input: #sides"() {
        when:
        new Polygon(sides)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == sides

        // Blok where mówi „przeprowadź ten test z każdą z następujących wartości: wartość ujemna, zero, jeden i dwa”
        where:
        sides << [-1, 0, 1, 2]
    }

    def "should be able to create a polygon with #sides sides"() {
        when:
        def polygon = new Polygon(sides)

        then:
        polygon.numberOfSides == sides

        where:
        sides << [3, 4, 5, 8, 14]
    }

    def "should be able to create a polygon with #sides sides with expect"() {
        expect:
        new Polygon(sides).numberOfSides == sides

        where:
        sides << [3, 4, 5, 8, 14]
    }

    def "should use data tables for calculating max"() {
        expect:
        Math.max(a, b) == max

        where:
        a | b | max
        1 | 3 | 3
        7 | 4 | 7
        0 | 0 | 0
    }

    def "should use data tables for calculating max. Max of #a and #b is #max"() {
        expect:
        Math.max(a, b) == max

        where:
        a | b || max
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }

    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    // mokowanie, mokowanie, mokowanie, mokowanie, mokowanie, mokowanie, mokowanie
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    def "should be able to mock a concrete class"() {
        given:
        Renderer renderer = Mock()
        // lub, ale tutaj brak typowania
        // def renderer = Mock(Renderer)
        def polygon = new Polygon(4, renderer)

        when:
        polygon.draw()

        // oznacze że test oczekuje wywołania cztery razy metody drawLine
        then:
        4 * renderer.drawLine()
    }

    // Stubs są przydatne do dostarczania danych lub wartości do testowanego kodu.
    def "should be able to create a stub"() {
        given:
        Palette palette = Stub()

        // jeśli wywołana zostanie metoda getPrimaryColour() to wygeneruj  Colour.Red
        palette.getPrimaryColour() >> Colour.Red
        def renderer = new Renderer(palette)

        expect:
        renderer.getForegroundColour() == Colour.Red
    }


    // Stubs są przydatne do dostarczania danych lub wartości do testowanego kodu.
    def "should be able to create a stub2"() {
        given:
        Palette palette = Mock()

        // jeśli wywołana zostanie metoda getPrimaryColour() to wygeneruj  Colour.Red
        palette.getPrimaryColour() >> Colour.Red
        def renderer = new Renderer(palette)

        expect:
        renderer.getForegroundColour() == Colour.Red
    }


    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    def "should use a helper method"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        polygon.numberOfSides == 4
        polygon.renderer == renderer
        //could check lots of different values on this polygon...
    }

//    @Ignore // wszystkie inne metody zostaną wykonane oprócz tej
    def "HashMap accepts null key"() {
        setup:
        def map = new HashMap()
        map.put(null, "elem")
    }
//    @IgnoreRest // tylko ta metoda zostanie wykonana
    def "HashMap accepts null key2"() {
        given: "inicjalizacja mapy"
        def map = new HashMap()

        when:
        map.put(null, "elem")

        then:
        notThrown(NullPointerException)
    }

    def "should check something"() {
        expect:
        verifyAll {
            2 == 2
            4 == 4
        }
    }

    @Unroll("maksymalnie #a i #b to #c")
    def "maksymalnie dwie liczby"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b | c
        3 | 7 | 7
        5 | 4 | 1
        9 | 9 | 9
    }

//    @Shared sql = Sql.newInstance("jdbc:h2:mem:", "org.h2.Driver")
//    def "maximum of two numbers"() {
//        expect:
//        Math.max(a, b) == c
//
//        where:
//        [a, b, c] << sql.rows("select a, b, c from maxdata")
//    }

    def "type coercion for data variable values"(Integer i) {
        expect:
        i instanceof Integer
        i == 10

        where:
        i = "10"
    }



}

@Use(CoerceBazToBar)
class Foo extends Specification {
    def foo(Bar bar) {
        expect:
        bar == Bar.FOO

        where:
        bar = Baz.FOO
    }
}
enum Bar { FOO, BAR }
enum Baz { FOO, BAR }
class CoerceBazToBar {
    static Bar asType(Baz self, Class<Bar> clazz) {
        return Bar.valueOf(self.name())
    }
}
