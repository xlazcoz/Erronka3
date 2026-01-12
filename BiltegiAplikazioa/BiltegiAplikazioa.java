import java.util.*;

public class BiltegiAplikazioa {
    public static void main(String[] args) {
        Biltegia biltegia = new Biltegia("IndiUsurbil Biltegia");
        Apalategia apalategiaA = new Apalategia("A", 5, 5);

        biltegia.gehituApalategia(apalategiaA);

        InbentarioZerbitzua inbentarioZerbitzua = new InbentarioZerbitzua(biltegia);
        UI ui = new UI(inbentarioZerbitzua, apalategiaA);

        ui.hasi();
    }
}

class UI {
    private final InbentarioZerbitzua inbentarioZerbitzua;
    private final Apalategia apalategia;
    private final Scanner scanner = new Scanner(System.in);

    public UI(InbentarioZerbitzua inbentarioZerbitzua, Apalategia apalategia) {
        this.inbentarioZerbitzua = inbentarioZerbitzua;
        this.apalategia = apalategia;
    }

    public void hasi() {
        while (true) {
            menuaInprimatu();
            int aukera = irakurriZenbakia("Aukeratu aukera bat: ");

            switch (aukera) {
                case 1 -> produktuaGehitu();
                case 2 -> produktuaKendu();
                case 3 -> produktuaMugitu();
                case 4 -> gelaxkaIkusi();
                case 5 -> produktuaBilatu();
                case 6 -> ProduktuAldaeraKontsultatu();
                case 7 -> inbentarioZerbitzua.inbentarioOsoaInprimatu();

                case 0 -> {
                    System.out.println("Agur! Programa ixten...");
                    return;
                }

                default -> System.out.println("Aukera baliogabea");
            }
        }
    }
    /*Menura imprimatzeko funtzioa */
    private void menuaInprimatu() {
        System.out.println("\n=== BILTEGIAREN MENU NAGUSIA ===\n");
        System.out.println("1. Produktua gelaxka batean sartu");
        System.out.println("2. Produktua gelaxkatik kendu");
        System.out.println("3. Produktua gelaxken artean mugitu");
        System.out.println("4. Gelaxka baten edukia ikusi");
        System.out.println("5. Produktu baten kokapena bilatu");
        System.out.println("6. Produktu aldaerak kontsultatu");
        System.out.println("7. Inbentario osoa ikusi\n");
        System.out.println("0. Irten");
    }

    private void produktuaGehitu() {
        Produktua produktua = produktuaIrakurri();
        Gelaxka gelaxka = gelaxkaIrakurri();
        int kantitatea = irakurriZenbakia("Kantitatea: ");

        inbentarioZerbitzua.produktuaSartu(gelaxka, produktua, kantitatea);
        System.out.println("Produktua behar bezala gehitu da.");
    }

    private void produktuaKendu() {
        String ean = irakurriTestua("EAN-13 kodea: ");
        Gelaxka gelaxka = gelaxkaIrakurri();
        int kantitatea = irakurriZenbakia("Kendu beharreko kantitatea: ");

        inbentarioZerbitzua.produktuaKenduEANarekin(gelaxka, ean, kantitatea);
        System.out.println("Produktua kendu da.");
    }

    private void produktuaMugitu() {
        String ean = irakurriTestua("EAN-13 kodea: ");

        System.out.println("Jatorrizko gelaxka:");
        Gelaxka jatorria = gelaxkaIrakurri();

        System.out.println("Helmugako gelaxka:");
        Gelaxka helmuga = gelaxkaIrakurri();

        int kantitatea = irakurriZenbakia("Kantitatea: ");
        inbentarioZerbitzua.produktuaMugituEANarekin(jatorria, helmuga, ean, kantitatea);

        System.out.println("Produktua mugitu da.");
    }

    private void gelaxkaIkusi() {
        Gelaxka gelaxka = gelaxkaIrakurri();
        inbentarioZerbitzua.gelaxkaInprimatu(gelaxka);
    }

    private void produktuaBilatu() {
        String ean = irakurriTestua("EAN-13 kodea: ");
        inbentarioZerbitzua.produktuarenKokapenaInprimatu(ean);
    }

    private Produktua produktuaIrakurri() {
        String ean = irakurriTestua("EAN-13 kodea: ");
        String izena = irakurriTestua("Produktuaren izena: ");
        String taila;
    while (true) {
        taila = irakurriTestua("Taila: ");
        if (taila.matches("^(XS|S|M|L|XL)$")) {
            break;
        }
        System.out.println("Talla no válida. Debe ser XS, S, M, L o XL. Inténtalo de nuevo.");
    }
        String kolorea = irakurriTestua("Kolorea: ");

        Map<String, String> atributuak = new HashMap<>();
        atributuak.put("taila", taila);
        atributuak.put("kolorea", kolorea);

        Kategoria kategoria = new Kategoria("Orokorra", null);

        return new Produktua(ean, izena, atributuak, kategoria, List.of());
    }


    private Gelaxka gelaxkaIrakurri() {
    int errenkada;
    int zutabea;
    
    // Errenkada Ondo dagoela ziurtatu
    while(true){
        errenkada = irakurriZenbakia("Errenkada: ");
        if(errenkada >= 0 && errenkada < 6){
            break;
        }
        System.out.println("Errenkada ez da baliozkoa. 0 eta 5 artean egon behar da.");
    }
    
    // Zutabea Ondo dagoela ziurtatu
    while(true){
        zutabea = irakurriZenbakia("Zutabea: ");
        if(zutabea >= 0 && zutabea < 6){
            break;
        }
        System.out.println("Zutabea ez da baliozkoa. 0 eta 5 artean egon behar da.");
    }

    return apalategia.getGelaxka(errenkada, zutabea);
}

    private int irakurriZenbakia(String mezua) {
        System.out.print(mezua);
        return Integer.parseInt(scanner.nextLine());
    }

    private String irakurriTestua(String mezua) {
        System.out.print(mezua);
        return scanner.nextLine();
    }
    private void ProduktuAldaeraKontsultatu() {
        String izena = irakurriTestua("Sartu produktuaren izena (edo zati bat): ");
        inbentarioZerbitzua.ProduktuAldaeraKontsultatu(izena);
    }
}

class Biltegia {
    private final String izena;
    private final List<Apalategia> apalategiak = new ArrayList<>();

    public Biltegia(String izena) {
        this.izena = izena;
    }

    public void gehituApalategia(Apalategia apalategia) {
        apalategiak.add(apalategia);
    }

    public List<Apalategia> getApalategiak() {
        return apalategiak;
    }
}

class Apalategia {
    private final String id;
    private final Gelaxka[][] gelaxkak;

    public Apalategia(String id, int errenkadak, int zutabeak) {
        this.id = id;
        this.gelaxkak = new Gelaxka[errenkadak][zutabeak];

        for (int r = 0; r < errenkadak; r++) {
            for (int c = 0; c < zutabeak; c++) {
                gelaxkak[r][c] = new Gelaxka(this, r, c);
            }
        }
    }

    public Gelaxka getGelaxka(int row, int col) {
        return gelaxkak[row][col];
    }

    public int getErrenkadak() {
        return gelaxkak.length;
    }

    public int getZutabeak() {
        return gelaxkak[0].length;
    }

    public String getId() {
        return id;
    }
}

class Gelaxka {
    private final Apalategia apalategia;
    private final int row;
    private final int col;
    private final Map<Produktua, Integer> produktuak = new HashMap<>();

    public Gelaxka(Apalategia apalategia, int row, int col) {
        this.apalategia = apalategia;
        this.row = row;
        this.col = col;
    }

    public void produktuaGehitu(Produktua p, int k) {
        produktuak.merge(p, k, Integer::sum);
    }

    public void produktuaKendu(Produktua p, int k) {
        produktuak.computeIfPresent(p, (prod, q) -> q - k <= 0 ? null : q - k);
    }

    public Map<Produktua, Integer> getProduktuak() {
        return produktuak;
    }

    public String kokapena() {
        return apalategia.getId() + "-" + row + "-" + col;
    }
}

class Produktua {
    private final String ean13;
    private final String izena;
    private final Map<String, String> atributuak;
    private final Kategoria kategoriaNagusia;
    private final List<Kategoria> bigarrenMailakoKategoriak;

    public Produktua(
        String ean13,
        String izena,
        Map<String, String> atributuak,
        Kategoria kategoriaNagusia,
        List<Kategoria> bigarrenMailakoKategoriak
    ) {
        this.ean13 = ean13;
        this.izena = izena;
        this.atributuak = atributuak;
        this.kategoriaNagusia = kategoriaNagusia;
        this.bigarrenMailakoKategoriak = bigarrenMailakoKategoriak;
    }
   public String getIzena() {
        return izena;
    }

    public Map<String, String> getAtributuak() {
        return atributuak;
    }
    public String getEan13() {
        return ean13;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produktua)) return false;
        Produktua p = (Produktua) o;
        return Objects.equals(ean13, p.ean13) && 
               Objects.equals(atributuak, p.atributuak);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ean13, atributuak);
    }

    @Override
    public String toString() {
        return izena + " " + atributuak + " (" + ean13 + ")";
    }
}

class Kategoria {
    private final String izena;
    private final Kategoria gurasoa;

    public Kategoria(String izena, Kategoria gurasoa) {
        this.izena = izena;
        this.gurasoa = gurasoa;
    }
}

class InbentarioZerbitzua {
    private final Biltegia biltegia;

    public InbentarioZerbitzua(Biltegia biltegia) {
        this.biltegia = biltegia;
    }

    public void produktuaSartu(Gelaxka gelaxka, Produktua produktua, int kantitatea) {
        gelaxka.produktuaGehitu(produktua, kantitatea);
    }

    public void produktuaKenduEANarekin(Gelaxka gelaxka, String ean, int kantitatea) {
        bilatuProduktua(gelaxka, ean).ifPresent(p -> {
            gelaxka.produktuaKendu(p, kantitatea);
        });
    }

    public void produktuaMugituEANarekin(Gelaxka jatorria, Gelaxka helmuga, String ean, int kantitatea) {
        bilatuProduktua(jatorria, ean).ifPresent(p -> {
            jatorria.produktuaKendu(p, kantitatea);
            helmuga.produktuaGehitu(p, kantitatea);
        });
    }

    public void gelaxkaInprimatu(Gelaxka gelaxka) {
        System.out.println("Gelaxka " + gelaxka.kokapena());
        gelaxka.getProduktuak().forEach((p, q) -> System.out.println("  " + p + " -> " + q));
    }
    /* Izenaren bidez bilatzeko metodo flexiblea */
    public void ProduktuAldaeraKontsultatu(String bilatzekoIzena) {
        System.out.println("\n=== ALDAERAK KONTSULTATZEN: '" + bilatzekoIzena + "' ===");
        
        Set<Produktua> aldaerak = new HashSet<>();
        
        
        String izenaMin = bilatzekoIzena.toLowerCase();

        for (Apalategia a : biltegia.getApalategiak()) {
            for (int r = 0; r < a.getErrenkadak(); r++) {
                for (int c = 0; c < a.getZutabeak(); c++) {
                    Gelaxka g = a.getGelaxka(r, c);
                    
                    g.getProduktuak().keySet().stream()
                        /*Izenan bidez filtratzen dugu, ez EAN kodearen bidez */
                        .filter(p -> p.getIzena().toLowerCase().contains(izenaMin))
                        .forEach(p -> aldaerak.add(p));
                }
            }
        }

        if (aldaerak.isEmpty()) {
            System.out.println("Ez da produkturik aurkitu izen horrekin.");
        } else {
            System.out.println("Aurkitutako aldaerak:");
            for (Produktua p : aldaerak) {
                String taila = p.getAtributuak().getOrDefault("taila", "N/A");
                String kolorea = p.getAtributuak().getOrDefault("kolorea", "N/A");
                
            
                System.out.println("-> " + p.getIzena() + " - " + taila + " - " + kolorea + " (EAN: " + p.getEan13() + ")");
            }
        }
    }
    public void produktuarenKokapenaInprimatu(String ean) {
        for (Apalategia a : biltegia.getApalategiak()) {
            for (int r = 0; r < a.getErrenkadak(); r++) {
                for (int c = 0; c < a.getZutabeak(); c++) {
                    Gelaxka g = a.getGelaxka(r, c);
                    bilatuProduktua(g, ean).ifPresent(p -> {
                        System.out.println(g.kokapena() + " -> " + g.getProduktuak().get(p));
                    });
                }
            }
        }
    }

    public void inbentarioOsoaInprimatu() {
        for (Apalategia a : biltegia.getApalategiak()) {
            for (int r = 0; r < a.getErrenkadak(); r++) {
                for (int c = 0; c < a.getZutabeak(); c++) {
                    Gelaxka g = a.getGelaxka(r, c);

                    if (!g.getProduktuak().isEmpty()) {
                        gelaxkaInprimatu(g);
                    }
                }
            }
        }
    }

    private Optional<Produktua> bilatuProduktua(Gelaxka gelaxka, String ean) {
        return gelaxka.getProduktuak()
            .keySet()
            .stream()
            .filter(p -> p.getEan13().equals(ean))
            .findFirst();
    }
}
