#version 400 core

in vec3 position;
in vec2 textureCoords;
in vec4 posx;

out vec3 colour;
out vec2 pass_textureCoords;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;


void main(){
   gl_Position = projectionMatrix * transformationMatrix * vec4(position, 1.0);
    pass_textureCoords = textureCoords;
}