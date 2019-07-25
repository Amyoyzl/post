package com.oocl.ita.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.ita.model.Pack;
import com.oocl.ita.servie.PackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PackController.class)
public class PackControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PackService packService;

    @Test
    public void should_return_all_package() throws Exception {
        Pack pack1 = new Pack();
        pack1.setId("123434");
        pack1.setName("test");
        pack1.setPhone("25445134431");
        Pack pack2 = new Pack();
        pack2.setId("123445");
        pack2.setName("test");
        pack2.setPhone("25445634431");
        List<Pack> packages = new ArrayList<>();
        packages.add(pack1);
        packages.add(pack2);

        when(packService.getAll()).thenReturn(packages);
        ResultActions resultActions = mvc.perform(get("/packages"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(packages.get(0).getName())))
                .andExpect(jsonPath("$[1].id", is(packages.get(1).getId())));

        verify(packService).getAll();
    }

    @Test
    public void should_return_consistent_packages_given_state() throws Exception {
        Pack pack1 = new Pack();
        pack1.setId("123434");
        pack1.setName("test");
        pack1.setState("已预约");
        pack1.setPhone("25445134431");
        Pack pack2 = new Pack();
        pack2.setId("123445");
        pack2.setName("test");
        pack2.setState("已预约");
        pack2.setPhone("25445634431");
        List<Pack> packages = new ArrayList<>();
        packages.add(pack1);
        packages.add(pack2);
        String state = "已预约";

        when(packService.getByState(state)).thenReturn(packages);
        ResultActions resultActions = mvc.perform(get("/packages").param("state", "已预约"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(packages.get(0).getName())))
                .andExpect(jsonPath("$[1].id", is(packages.get(1).getId())));

        verify(packService).getByState(anyString());
    }

    @Test
    public void should_set_package_state() throws Exception {
        Pack pack = new Pack();
        pack.setId("3412435");
        pack.setName("test");
        pack.setPhone("12574474367");
        pack.setState("已取件");
        String state = "已取件";
        String id = "3412435";

        when(packService.setState(id, state)).thenReturn(pack);
        ResultActions resultActions = mvc.perform(patch("/packages/"+id).param("state", state));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(pack.getId())))
                .andExpect(jsonPath("$.state", is(state)));

        verify(packService).setState(anyString(), anyString());

    }

    @Test
    public void should_add_package() throws Exception {
        Pack pack = new Pack();
        pack.setId("3412435");
        pack.setName("test");
        pack.setPhone("12574474367");
        pack.setState("未取件");

        when(packService.add(any())).thenReturn(pack);
        ResultActions resultActions = mvc.perform(post("/packages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pack)));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(pack.getId())))
                .andExpect(jsonPath("$.state", is(pack.getState())));

        verify(packService).add(any());

    }

    @Test
    public void should_set_package_time() throws Exception {
        Pack pack = new Pack();
        pack.setId("3412435");
        pack.setName("test");
        pack.setPhone("12574474367");
        pack.setState("已预约");
        pack.setTime(new Date());

        when(packService.setTime(anyString(), any())).thenReturn(pack);
        ResultActions resultActions = mvc.perform(patch("/packages/3412435")
                .param("time", "2019-02-12 18:20:00"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(pack.getId())))
                .andExpect(jsonPath("$.state", is(pack.getState())));

        verify(packService).setTime(anyString(), anyString());

    }

}
