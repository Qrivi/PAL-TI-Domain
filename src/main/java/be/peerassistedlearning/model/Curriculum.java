/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Matthias Hannes Koen Demonie David Op de Beeck
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package be.peerassistedlearning.model;

/**
 * Class that specifies the curriculums
 */
public enum Curriculum{

    TI( "Toegepaste Informatica" ),
    B_MGMT( "Bedrijfsmanagement" ),
    O_MGMT( "Office Management" );

    private final String name;

    Curriculum( String name ){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static Curriculum getByName( String name ){
        for( Curriculum c : values() ){
            String s = c.toString();
            if( s.equalsIgnoreCase( name ) )
                return c;
        }
        return null;
    }

    public static Curriculum getFromProgramme( String programme ){
        if( programme == null )
            return null;

        for( Curriculum c : values() ){
            String s = c.getName().toLowerCase();
            if( programme.toLowerCase().contains( s ) )
                return c;
        }
        return null;
    }
}
