package cl.coopeuch.backdev.common.view;

public class View {
    public interface Hide {};
    public interface Simple {};
    public interface WithRelations extends Simple {};
    public interface WithList extends WithRelations{};
}
