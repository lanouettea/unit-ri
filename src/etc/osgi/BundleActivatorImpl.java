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
package org.unitsofmeasurement.ri.internal.osgi;

import javolution.context.LogContext;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.unitsofmeasurement.service.SystemOfUnitsService;
import org.unitsofmeasurement.service.UnitFormatService;

/**
 * <p> The OSGi activator for the unit-ri bundle.</p>
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 5.1, April 8, 2014
 */
public class BundleActivatorImpl implements BundleActivator {

    public void start(BundleContext bc) throws Exception {
        Object name = bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME);
        Object version = bc.getBundle().getHeaders().get(Constants.BUNDLE_VERSION);
        LogContext.info("Start Bundle: ", name, ", Version: ", version);
        
        // Publish SystemOfUnitsServices Implementation.
         bc.registerService(SystemOfUnitsService.class.getName(), new SystemOfUnitsServiceImpl(), null);

        // Publish UnitFormatService Implementation.
         bc.registerService(UnitFormatService.class.getName(), new UnitFormatServiceImpl(), null);
        
    }

    public void stop(BundleContext bc) throws Exception {
        Object name = bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME);
        Object version = bc.getBundle().getHeaders().get(Constants.BUNDLE_VERSION);
        LogContext.info("Stop Bundle: ", name, ", Version: ", version);
    }
        
}
