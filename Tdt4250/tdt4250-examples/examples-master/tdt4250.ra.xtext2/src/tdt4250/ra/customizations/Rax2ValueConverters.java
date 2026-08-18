package tdt4250.ra.customizations;

import org.eclipse.xtext.common.services.Ecore2XtextTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.AbstractNullSafeConverter;
import org.eclipse.xtext.nodemodel.INode;

public class Rax2ValueConverters extends Ecore2XtextTerminalConverters {
	
	@ValueConverter(rule = "FactorAsPercentage")
	public IValueConverter<Float> FactorAsPercentage() {
	   return new AbstractNullSafeConverter<Float>() {
		@Override
		protected Float internalToValue(String string, INode node) throws ValueConverterException {
			if (string.endsWith("%")) {
				string = string.substring(0, string.length() - 1);
			}
			return Float.valueOf(string) / 100;
		}
		@Override
		public String internalToString(Float value) {
			return super.toString(value * 100) + "%";
		}
	   };
	}
}
