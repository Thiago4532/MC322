package lab05;

import java.util.ArrayList;

// Abstração pra facilitar implementação de Menus
public class Menu {
    private record MenuEntry(String name, Runnable method) { }

    private final ArrayList<MenuEntry> methods;
    private String optionMsg;

    Menu() {
        this.methods = new ArrayList<>();
        this.optionMsg = "Escolha uma opção: ";
    }

    public String getOptionMsg() { return optionMsg; }
    public void setOptionMsg(String optionMsg) { this.optionMsg = optionMsg; }

    public void add(String name, Runnable r) {
        methods.add(new MenuEntry(name, r));
    }

    public Runnable getMethod(String name) {
        for (MenuEntry menuEntry : methods) {
            if (menuEntry.name == name)
                return menuEntry.method;
        }
        return null;
    }

    public boolean runOnce() {
        for (;;) {
            for (int i = 0; i < methods.size(); i++) {
                System.out.printf("%d) %s\n", i + 1, methods.get(i).name);
            }
            System.out.printf("%d) Sair\n", methods.size() + 1);

            System.out.println("");
            System.out.print(optionMsg);

            int num = ReadUtil.readInt();
            if (num < 1 || num > methods.size() + 1) {
                System.out.println("Opção inválida!");
                return true;
            }

            if (num == methods.size() + 1)
                return false;

            methods.get(num - 1).method.run();
            return true;
        }
    }

    public void run() {
        while(runOnce()) {}
    }
}
