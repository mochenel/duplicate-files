package com.example.demo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;

@Route
public class VaadinUI extends UI{
	public VaadinUI() {
		add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));
	}

}
