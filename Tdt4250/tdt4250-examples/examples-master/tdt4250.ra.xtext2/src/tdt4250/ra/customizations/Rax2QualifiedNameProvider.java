package tdt4250.ra.customizations;

import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

import tdt4250.ra.Course;

public class Rax2QualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {

	public QualifiedName qualifiedName(Course course) {
		return QualifiedName.create(course.getCode());
	}
}
