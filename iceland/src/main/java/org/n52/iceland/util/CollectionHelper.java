/**
 * Copyright 2015 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.iceland.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 *         J&uuml;rrens</a>
 * 
 * @since 4.0.0
 */
public final class CollectionHelper {
    private CollectionHelper() {
    }

    /**
     * @param entries
     *            the <i>final</i> set of entries to add to the newly created
     *            <i>unmodifiable</i> map
     * @return an <i>unmodifiable</i> map with all given entries
     */
    public static <K, V> Map<K, V> map(final Entry<K, V>... entries) {
        final HashMap<K, V> map = new HashMap<K, V>(entries.length);
        for (final Entry<K, V> entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }
        return Collections.unmodifiableMap(map);
    }

    /**
     * @return an <b>UNMODIFIABLE</b> List&lt;T&gt;
     */
    public static <T> List<T> list(final T... elements) {
        return Collections.unmodifiableList(Arrays.asList(elements));
    }

    public static <T> Set<T> union(final Set<T>... elements) {
        return ((elements.length == 0) ? Collections.<T> emptySet() : new HashSet<T>(elements.length
                * elements[0].size()) {
            private static final long serialVersionUID = -3161916411604210423L;
            {
                for (final Set<T> s : elements) {
                    addAll(s);
                }
            }
        });
    }

    public static <T> Set<T> union(final Iterable<Set<T>> elements) {
        return new HashSet<T>() {
            private static final long serialVersionUID = -3161916411604210423L;
            {
                for (final Set<T> s : elements) {
                    addAll(s);
                }
            }
        };
    }

    /**
     * @return an <b>UNMODIFIABLE</b> Set&lt;T&gt;
     */
    public static <T> Set<T> unmodifiableSet(final Set<? extends T> s) {
        return (s == null) ? Collections.<T> emptySet() : Collections.unmodifiableSet(s);
    }

    /**
     * @return an <b>UNMODIFIABLE</b> Map&lt;K, V&gt;
     */
    public static <K, V> Map<K, V> unmodifiableMap(final Map<? extends K, ? extends V> m) {
        return (m == null) ? Collections.<K, V> emptyMap() : Collections.unmodifiableMap(m);
    }

    /**
     * @return an <b>UNMODIFIABLE</b> Collection&lt;T&gt;
     */
    public static <T> Collection<T> unmodifiableCollection(final Collection<? extends T> c) {
        return (c == null) ? Collections.<T> emptyList() : Collections.unmodifiableCollection(c);
    }

    /**
     * @return an <b>UNMODIFIABLE</b> List&lt;T&gt;
     */
    public static <T> List<T> unmodifiableList(final List<? extends T> l) {
        return (l == null) ? Collections.<T> emptyList() : Collections.unmodifiableList(l);
    }

    public static <T> List<T> conjunctCollections(final Collection<T> list1, final Collection<T> list2) {
        final HashSet<T> s1 = new HashSet<T>(list1);
        s1.retainAll(list2);
        return new ArrayList<T>(s1);
    }
    
    public static <T> Set<T> conjunctCollectionsToSet(final Collection<T> list1, final Collection<T> list2) {
        final HashSet<T> s1 = new HashSet<T>(list1);
        s1.retainAll(list2);
        return s1;
    }

    public static <K, V> Map<K, V> synchronizedInitialSizeMapWithLoadFactor1(final int capacity) {
        return CollectionHelper.synchronizedMap(capacity, 1.0F);
    }

    public static <K, V> Map<K, V> synchronizedMap() {
        return Collections.synchronizedMap(Maps.<K, V> newHashMap());
    }

    public static <K, V> Map<K, V> synchronizedMap(final int initialCapacity) {
        return Collections.synchronizedMap(new HashMap<K, V>(initialCapacity));
    }

    public static <K, V> Map<K, V> synchronizedMap(final int initialCapacity, final float loadFactor) {
        return Collections.synchronizedMap(new HashMap<K, V>(initialCapacity, loadFactor));
    }

    /**
     * Constructs a new synchronized {@code Set} based on a {@link HashSet}.
     * 
     * @return a synchronized Set
     */
    public static <T> Set<T> synchronizedSet() {
        return Collections.synchronizedSet(Sets.<T> newHashSet());
    }

    /**
     * Constructs a new synchronized {@code Set} based on a {@link HashSet} with
     * the specified {@code initialCapacity}.
     * 
     * @param initialCapacity
     *            the initial capacity of the set
     * 
     * @return a synchronized Set
     */
    public static <T> Set<T> synchronizedSet(final int initialCapacity) {
        return Collections.synchronizedSet(new HashSet<T>(initialCapacity));
    }

    /**
     * Constructs a new synchronized {@code List} based on a {@link LinkedList}.
     * 
     * @return a synchronized List
     */
    public static <E> List<E> synchronizedList() {
        return Collections.synchronizedList(Lists.<E> newLinkedList());
    }

    /**
     * Constructs a new synchronized {@code List} based on a {@link ArrayList}
     * with the specified {@code initialCapacity}.
     * 
     * @param initialCapacity
     *            the initial capacity of the array list
     * 
     * @return a synchronized List
     */
    public static <E> List<E> synchronizedList(final int initialCapacity) {
        return Collections.synchronizedList(Lists.<E> newArrayListWithCapacity(initialCapacity));
    }

    /**
     * @param collectionOfCollection
     *            a Collection&lt;Collection&lt;T>>
     * 
     * @return a Set&lt;T> containing all values of all Collections&lt;T>
     *         without any duplicates
     */
    public static <T> Set<T> unionOfListOfLists(final Collection<? extends Collection<T>> collectionOfCollection) {
        if (collectionOfCollection == null || collectionOfCollection.isEmpty()) {
            return Collections.emptySet();
        }
        final HashSet<T> union = new HashSet<T>();
        for (final Collection<T> col : collectionOfCollection) {
            if (col != null) {
                for (final T t : col) {
                    if (t != null) {
                        union.add(t);
                    }
                }
            }
        }
        return union;
    }

    /**
     * Check if collection is not null and not empty
     * 
     * @param collection
     *            Collection to check
     * 
     * @return empty or not
     */
    public static <T> boolean isNotEmpty(final Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * Check if collection is not <tt>null</tt> and empty
     * 
     * @param collection
     *            Collection to check
     * 
     * @return <tt>true</tt>, if collection is not null and empty, else
     *         <tt>false</tt>
     */
    public static <T> boolean isEmpty(final Collection<T> collection) {
        return collection != null && collection.isEmpty();
    }

    /**
     * Check if collection is not null and not empty
     * 
     * 
     * @param map
     *            Map to check
     * 
     * @return <tt>false</tt>, if map is <tt>null</tt> or empty, else
     *         <tt>true</tt>.
     */
    public static <K, V> boolean isNotEmpty(final Map<K, V> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * Check if map is not <tt>null</tt> and empty
     * 
     * @param map
     *            map to check
     * 
     * @return <tt>true</tt>, if map is not null and empty, else <tt>false</tt>
     */
    public static <K, V> boolean isEmpty(final Map<K, V> map) {
        return map != null && map.isEmpty();
    }

    /**
     * Reverses a map (switches key and value types).
     * 
     * @param <K>
     *            the key type
     * @param <V>
     *            the value type
     * @param map
     *            the map
     * 
     * @return the reversed map
     */
    public static <K, V> Map<V, K> reverse(final Map<K, V> map) {
        final Map<V, K> reversed = new HashMap<V, K>(map.size());
        for (final Entry<K, V> e : map.entrySet()) {
            reversed.put(e.getValue(), e.getKey());
        }
        return reversed;
    }

    /**
     * Examine a collection and determines if it is null, empty, or contains
     * only null values
     * 
     * @param collection
     *            Collection to examine
     * @return whether the collection is null, empty, or contains only nulls
     */
    public static boolean nullEmptyOrContainsOnlyNulls(final Collection<? extends Object> collection) {
        if (isNotEmpty(collection)) {
            for (final Object obj : collection) {
                if (obj != null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Check if array is not null and not empty
     * 
     * @param array
     *            Array to check
     * @return <code>true</code>, if array is not null and not empty
     */
    public static boolean isNotNullOrEmpty(Object[] array) {
        return array != null && array.length > 0;
    }

    /**
     * Check if array is not null or not empty
     * 
     * @param array
     *            Array to check
     * @return <code>true</code>, if array is null or empty
     */
    public static boolean isNullOrEmpty(Object[] array) {
        return !isNotNullOrEmpty(array);
    }

    public static String collectionToString(Collection<?> collection) {
        StringBuilder builder = new StringBuilder();
        builder.append(Constants.OPEN_BRACE_CHAR);
        if (isNotEmpty(collection)) {
            Iterator<?> iterator = collection.iterator();
            while (iterator.hasNext()) {
                Object object = (Object) iterator.next();
                builder.append(object.toString());
                builder.append(Constants.COMMA_CHAR);
            }
            builder.deleteCharAt(builder.lastIndexOf(Constants.COMMA_STRING));
        }
        builder.append(Constants.CLOSE_BRACE_CHAR);
        return builder.toString();
    }

    /**
     * Add a value to a map collection, initializing the key's collection if needed
     * 
     * @param key Key whose value collection should be added to
     * @param valueToAdd Vale to add to the key's collection
     * @param map Map holding collections
     */
    public static <K, V> void addToCollectionMap(K key, V valueToAdd, Map<K,Collection<V>> map) {
        if (key == null || valueToAdd == null || map == null) {
            return;
        }
        Collection<V> collection = map.get(key);
        if (collection == null) {
            collection = Lists.newArrayList();
            map.put(key, collection);            
        }
        collection.add(valueToAdd);
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map) {
    	List<Map.Entry<K, V>> list = new LinkedList<>( map.entrySet() );
    	Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
    		@Override
    		public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
    			return (o1.getValue()).compareTo( o2.getValue() );
    		}
    	});
    	Map<K, V> result = new LinkedHashMap<>();
    	for (Map.Entry<K, V> entry : list) {
    		result.put( entry.getKey(), entry.getValue() );
    	}
    	return result;
    }

}
