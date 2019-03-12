interface HeapStringInterface  {  // reprezentujte Max-heap haldu, koreò haldy je max
    public String first();        // vráti najväèší prvok z haldy
    public String remove();        // odstráni najväèší prvok z haldy
    public void insert(String str);// pridá prvok str do haldy, halda zostane haldou
    public int size();            // vráti ve¾kos haldy, poèet prvkov
}