/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2014, Jean-Marie Dautelle, Werner Keil, V2COM and individual
 *  contributors by the @author tag.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither Unit-API nor the name of the copyright holders or contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tec.units.ri.quantity;

import javax.measure.Quantity;
import javax.measure.Unit;

import tec.units.ri.AbstractQuantity;

/**
 * An amount of quantity, consisting of a double and a Unit. FloatQuantity
 * objects are immutable.
 * 
 * @see AbstractQuantity
 * @see Quantity
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @author Otavio de Santana
 * @param <Q>
 *            The type of the quantity.
 * @version 0.2, $Date: 2014-08-02 $
 */
final class FloatQuantity<T extends Quantity<T>> extends AbstractQuantity<T> {

	final float value;

    public FloatQuantity(float value, Unit<T> unit) {
    	super(unit);
        this.value = value;
    }

    @Override
    public Float getValue() {
        return value;
    }

    // Implements AbstractQuantity
    public double doubleValue(Unit<T> unit) {
        return (super.getUnit().equals(unit)) ? value : super.getUnit().getConverterTo(unit).convert(value);
    }

	public long longValue(Unit<T> unit) {
        double result = doubleValue(unit);
        if ((result < Long.MIN_VALUE) || (result > Long.MAX_VALUE)) {
            throw new ArithmeticException("Overflow (" + result + ")");
        }
        return (long) result;
	}

	@Override
	public AbstractQuantity<T> add(Quantity<T> that) {
		return BaseQuantity.of(value + that.getValue().floatValue(), getUnit()); // TODO use shift of the unit?
	}

	@Override
	public AbstractQuantity<T> subtract(Quantity<T> that) {
		return BaseQuantity.of(value - that.getValue().floatValue(), getUnit()); // TODO use shift of the unit?
	}

	@SuppressWarnings("unchecked")
	@Override
	public AbstractQuantity<T> multiply(Quantity<?> that) {
		return (AbstractQuantity<T>) BaseQuantity.of(value * that.getValue().floatValue(), 
				getUnit().multiply(that.getUnit()));
	}

	@Override
	public Quantity<T> multiply(Number that) {
		return BaseQuantity.of(value * that.floatValue(), 
				getUnit().multiply(that.doubleValue()));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Quantity<?> divide(Quantity<?> that) {
		return new FloatQuantity(value / that.getValue().floatValue(), getUnit().divide(that.getUnit()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Quantity<T> inverse() {
		return (AbstractQuantity<T>) BaseQuantity.of(value, getUnit().inverse());
	}

	@Override
	public Quantity<T> divide(Number that) {
		return BaseQuantity.of(value / that.floatValue(), getUnit());
	}
}