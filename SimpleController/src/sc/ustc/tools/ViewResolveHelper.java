package sc.ustc.tools;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sc.ustc.model.view.Body;
import sc.ustc.model.view.Form;
import sc.ustc.model.view.Header;
import sc.ustc.model.view.View;
import sc.ustc.model.view.widgets.ButtonView;
import sc.ustc.model.view.widgets.CheckBoxView;
import sc.ustc.model.view.widgets.TextView;
import sc.ustc.model.view.widgets.Widget;

import java.util.ArrayList;
import java.util.List;

public class ViewResolveHelper extends DefaultHandler {
    private View view;
    private Header header;
    private Body body;
    private Form form;
    private List<Widget> widgetList;
    private Widget widget;
    private String tagName;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public List<Widget> getWidgetList() {
        return widgetList;
    }

    public void setWidgetList(List<Widget> widgetList) {
        this.widgetList = widgetList;
    }

   
    @Override
    public void startDocument() throws SAXException {
        this.view = new View();
        this.widgetList = new ArrayList<>();
    }

    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName) {
            case "header":
                this.header = new Header();
                break;
            case "body":
                this.body = new Body();
                break;
            case "form":
                this.form = new Form();
                break;
            case "textView":
                this.widget = new TextView();
                break;
            case "buttonView":
                this.widget = new ButtonView();
                break;
            case "checkBoxView":
                this.widget = new CheckBoxView();
                break;
        }
        this.tagName = qName;
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName) {
            case "header":
                this.view.setHeader(this.header);
                break;
            case "body":
                this.view.setBody(this.body);
                break;
            case "form":
                this.body.setForm(this.form);
                break;
            case "textView":
            case "buttonView":
            case "checkBoxView":
                this.form.addWidgetList(widget);
                this.widget = null;
                break;
        }
        this.tagName = null;
    }


    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);

        String value = new String(ch, start, length);
        if (this.tagName == null) {
            return;
        }
        switch (this.tagName) {


            case "title":
                this.header.setTitle(value);
                break;

            case "name":
                if (this.widget != null) {
           
                    this.widget.setName(value);
                } else if (this.form != null) {
                 
                    this.form.setName(value);
                }
                break;

           
            case "action":
                this.form.setAction(value);
                break;

         
            case "method":
                this.form.setMethod(value);
                break;


            case "label":
                this.widget.setLabel(value);
                break;


            case "value":
                this.widget.setValue(value);
                break;

        }
    }

}
