/*
 * Copyright (c) 2002-2019 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4j.ogm.metadata.schema;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.neo4j.ogm.utils.RelationshipUtils;

/**
 * Node represents nodes in the schema
 *
 * @author Frantisek Hartman
 */
class NodeImpl implements Node {

    private final String label;
    private final Collection<String> labels;
    private final Map<String, Relationship> relationships = new HashMap<>();

    public NodeImpl(String label, Collection<String> labels) {
        this.label = label;
        this.labels = labels;
    }

    @Override
    public Optional<String> label() {
        return Optional.ofNullable(label);
    }

    @Override
    public Collection<String> labels() {
        return labels;
    }

    @Override
    public Map<String, Relationship> relationships() {
        return relationships;
    }

    void addRelationship(Relationship relationship) {
        relationships.putIfAbsent(RelationshipUtils.inferFieldName(relationship.type()), relationship);
    }

    void addRelationship(String name, Relationship relationship) {
        relationships.putIfAbsent(name, relationship);
    }

    @Override
    public String toString() {
        return "NodeImpl{" +
            "label='" + label + '\'' +
            ", labels=" + labels +
            '}';
    }
}
