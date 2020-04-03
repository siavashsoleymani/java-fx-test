module ir.siavash.helloworld {

	requires javafx.base;
	requires javafx.controls;

	opens ir.siavash.helloworld to javafx.graphics;
}