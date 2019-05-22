package org.eugen8.jsonapi.simpleapp.model;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Implements the Collection interface to store the list of Photo objects in a collection-like manner
 * @author Eugen
 *
 */
public class ListOfPhotos extends AbstractCollection<Photo> {
	
	private List<Photo> photos = new ArrayList<Photo>();

	public void forEach(Consumer<? super Photo> action) {
		photos.forEach(action);
	}

	public boolean isEmpty() {
		return photos.isEmpty();
	}

	public boolean contains(Object o) {
		return photos.contains(o);
	}

	public Object[] toArray() {
		return photos.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return photos.toArray(a);
	}

	public boolean add(Photo e) {
		return photos.add(e);
	}

	public boolean remove(Object o) {
		return photos.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return photos.containsAll(c);
	}

	public boolean addAll(Collection<? extends Photo> c) {
		return photos.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends Photo> c) {
		return photos.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return photos.removeAll(c);
	}

	public <T> T[] toArray(IntFunction<T[]> generator) {
		return photos.toArray(generator);
	}

	public boolean retainAll(Collection<?> c) {
		return photos.retainAll(c);
	}

	public void replaceAll(UnaryOperator<Photo> operator) {
		photos.replaceAll(operator);
	}

	public void sort(Comparator<? super Photo> c) {
		photos.sort(c);
	}

	public void clear() {
		photos.clear();
	}

	public boolean equals(Object o) {
		return photos.equals(o);
	}

	public int hashCode() {
		return photos.hashCode();
	}

	public Photo get(int index) {
		return photos.get(index);
	}

	public boolean removeIf(Predicate<? super Photo> filter) {
		return photos.removeIf(filter);
	}

	public Photo set(int index, Photo element) {
		return photos.set(index, element);
	}

	public void add(int index, Photo element) {
		photos.add(index, element);
	}

	public Photo remove(int index) {
		return photos.remove(index);
	}

	public int indexOf(Object o) {
		return photos.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return photos.lastIndexOf(o);
	}

	public ListIterator<Photo> listIterator() {
		return photos.listIterator();
	}

	public ListIterator<Photo> listIterator(int index) {
		return photos.listIterator(index);
	}

	public List<Photo> subList(int fromIndex, int toIndex) {
		return photos.subList(fromIndex, toIndex);
	}

	public Spliterator<Photo> spliterator() {
		return photos.spliterator();
	}

	public Stream<Photo> stream() {
		return photos.stream();
	}

	public Stream<Photo> parallelStream() {
		return photos.parallelStream();
	}

	private List<Photo> getAllPhotos(){
		return this.photos;
	}

	public int size() {
		return photos.size();
	}

	public Iterator<Photo> iterator() {
		return photos.iterator();
	}



}
