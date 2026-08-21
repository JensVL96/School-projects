# Modules to lint.
modules="users/ recipes/ ingredients/ inventory/ shopping_list/"


# Linting and saving results to file.
pylint --load-plugins pylint_django $modules \
	| tee ./pylint/pylint.log || pylint-exit $?
PYLINT_SCORE=$(sed -n 's/^Your code has been rated at \([-0-9.]*\)\/.*/\1/p' \
			   ./pylint/pylint.log)


# Generating badge based on results.
anybadge --label=Pylint --file=pylint/pylint.svg \
		 --value=$PYLINT_SCORE 2=red 4=orange 8=yellow 10=green
echo "Pylint score is $PYLINT_SCORE"
