package br.com.xfrontier.housekeeper.api.assembler;

import java.util.List;

public interface Assembler<R> {

    void addLinks(R resource);

    void addLinks(List<R> collectionResource);

}
