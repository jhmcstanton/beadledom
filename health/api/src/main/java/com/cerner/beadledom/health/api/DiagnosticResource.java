package com.cerner.beadledom.health.api;

import com.cerner.beadledom.health.dto.HealthDto;
import com.cerner.beadledom.health.dto.HealthJsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/health",
    description = "Diagnostic health check")
@Path("meta/health/diagnostic")
public interface DiagnosticResource {

  @GET
  @Produces(MediaType.TEXT_HTML)
  Response getDiagnosticHealthCheckHtml();

  @ApiOperation(value = "Diagnostic Health Check",
      notes =
          "The response JSON will contain all available diagnostic details and the results of "
              + "checking the health of all dependencies.",
      response = HealthDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 503, message = "unhealthy", response = HealthDto.class),
      @ApiResponse(code = 200, message = "healthy", response = HealthDto.class)})
  @Operation(summary = "Diagnostic Health Check",
      description = "The response JSON will contain all available diagnostic details and the "
          + "results of checking the health of all dependencies.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "healthy",
              content = @Content(schema = @Schema(implementation = HealthDto.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "503",
              description = "unhealthy",
              content = @Content(schema = @Schema(implementation = HealthDto.class)))
      }
  )
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @JsonView(HealthJsonViews.Diagnostic.class)
  Response getDiagnosticHealthCheck();
}
