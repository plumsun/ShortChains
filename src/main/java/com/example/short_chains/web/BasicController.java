/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.short_chains.web;

import com.example.short_chains.resp.Response;
import com.example.short_chains.service.SortChainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Basic controller.
 *
 * @author Lihaohan
 */
@RequiredArgsConstructor
@RequestMapping("/chains")
@RestController
public class BasicController {

    /**
     * The Service.
     */
    private final SortChainService service;

    /**
     * Hello response.
     *
     * @param url the url
     * @return the response
     */
    @GetMapping("/generation")
    public Response hello(@RequestParam(name = "url", defaultValue = "unknown user") String url) {
        return service.generation(url);
    }

    @GetMapping("/findLongChain")
    public Response findLongChain(@RequestParam(name = "sortChain", defaultValue = "unknown user") String sortChainUrl) {
        return service.findLongChain(sortChainUrl);
    }
}
