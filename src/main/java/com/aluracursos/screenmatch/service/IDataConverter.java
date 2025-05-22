package com.aluracursos.screenmatch.service;

public interface IDataConverter {
    <T> T obtainData(String json, Class<T> tClass);
}
