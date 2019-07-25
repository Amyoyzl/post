package com.oocl.ita.controller;

import com.oocl.ita.model.Pack;
import com.oocl.ita.servie.PackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

        resultActions.andExpect(jsonPath("$[0].name", is(packages.get(0).getName())))
                .andExpect(jsonPath("$[1].id", is(packages.get(1).getId())));

        verify(packService).getAll();

    }
}
