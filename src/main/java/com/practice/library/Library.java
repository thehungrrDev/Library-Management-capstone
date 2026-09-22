package com.practice.library;

import org.hibernate.persister.entity.SingleTableEntityPersister;

import java.util.*;
import java.io.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library {
    Map<Integer,KitabKhana> catalog = new HashMap<>();
    Set<KitabKhana> books = new HashSet<>();

    public void addbooks(KitabKhana book){
        books.add(book);
        catalog.put(book.getId(),book);
    }
    public void print(){
        System.out.println(catalog);
    }


    public void savetofile(String filename){
        try(BufferedWriter writer  = new BufferedWriter(new FileWriter(filename))){
            for(KitabKhana book : catalog.values()){
                writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + ","
                + book.getTotalCopies() + "," + book.getAvailableCopies());
                writer.newLine();
            }
            System.out.println(" Library saved to : " + filename);
        }
        catch(IOException e){
            System.out.println(" Something went wrong ");
        }
    }
    public void loadfromfile(String filename){
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = reader.readLine()) != null){
                String [] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title =parts[1];
                String author = parts[2];
                int Tc = Integer.parseInt(parts[3]);
                int Ac = Integer.parseInt(parts[4]);
                addbooks(new KitabKhana(id,title,author,Tc,Ac));
            }
            System.out.println(" Library loaded from : " + filename);
        }
        catch(IOException e){
            System.out.println(" NO existing Library found .... start fresh ");
        }
    }
    public <T> Optional<T> FindFirst(List<T> list, Predicate<T> condition){
        for(T item : list){
            if(condition.test(item)){return Optional.of(item);}
        }
        return Optional.empty();
    }
    public <T extends Number> double Average(List<T> list){
        double total=0;
        for(T  item : list){
            total+=item.doubleValue();
        }
        return total/list.size();
    }
    public  synchronized void BorrowBook(int BookId){
        KitabKhana kitab = Optional.ofNullable(catalog.get(BookId)).orElseThrow(() -> new BooksNotFound(BookId + " this Id is not found "));

        if(kitab.getAvailableCopies() <= 0){
            throw new NoCopiesAvaliable(kitab.getTitle() + " Has no avaliable Copies ");
        }
        kitab.setAvailableCopies(kitab.getAvailableCopies()-1);
    }

    public static void main ( String [] args){
        Library library = new Library();
        library.loadfromfile("library.txt");






        library.savetofile("library.txt");
    }

}
