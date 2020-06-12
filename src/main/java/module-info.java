module ir.siavash.helloworld {

	requires javafx.base;
	requires javafx.controls;
	requires com.rabbitmq.client;
	requires java.sql;
	requires javafx.web;

	opens ir.siavash.helloworld to javafx.graphics;
}