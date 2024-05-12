package bean;

import java.io.Serializable;
import jakarta.inject.Named;
//https://jakarta.ee/specifications/platform/9/apidocs/jakarta/faces/bean/viewscoped
import jakarta.faces.view.ViewScoped;
import org.primefaces.extensions.component.orgchart.OrgChartNode;
import org.primefaces.extensions.component.orgchart.DefaultOrgChartNode;
import org.primefaces.extensions.event.OrgChartClickEvent;
import org.primefaces.extensions.event.OrgChartDropEvent;

@Named
@ViewScoped
public class OrgchartController implements Serializable {

    private static final long serialVersionUID = 1648477595853984820L;

    private OrgChartNode orgChartNode;

    private String direction = "t2b";

    public OrgchartController() {
        super();
        init();
    }

    public void init() {
        orgChartNode = new DefaultOrgChartNode("id1", "Cohort March 2023", "2023-03-20");
        orgChartNode.addChild(new DefaultOrgChartNode("id2", "Cloud Basics", "Content2"));
        orgChartNode.addChild(new DefaultOrgChartNode("id3", "Programming Java", "Content3"));
        orgChartNode.addChild(new DefaultOrgChartNode("id4", "Programming Python", "Content3"));
        final OrgChartNode node = new DefaultOrgChartNode("id5", "Iac", "Infra as Code");
        orgChartNode.addChild(node);
        node.addChild(new DefaultOrgChartNode("id6", "pulumi", "with java programming language"));
        node.addChild(new DefaultOrgChartNode("id7", "terraform", "HCL Hashicorp config language"));
    }

    public static void onClick(final OrgChartClickEvent event) {
        System.out.println("clicked ID: " + event.getId());
        System.out.println("hierarchy: " + event.getHierarchy().toString());
    }

    public static void onDropOver(final OrgChartDropEvent event) {
        System.out.println("hierarchy: " + event.getHierarchy().toString());
        System.out.println("dragged node id " + event.getDraggedNodeId());
        System.out.println("dropped node id " + event.getDroppedZoneId());
    }

    public OrgChartNode getOrgChartNode() {
        return orgChartNode;
    }

    public void setOrgChartNode(final OrgChartNode orgChartNode) {
        this.orgChartNode = orgChartNode;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(final String direction) {
        this.direction = direction;
    }
}